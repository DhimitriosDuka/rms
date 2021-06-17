package com.rms.rms.service.implementation;

import com.rms.rms.dto.schedule.ScheduleCreateDto;
import com.rms.rms.dto.schedule.ScheduleResponseDto;
import com.rms.rms.dto.schedule.ScheduleUpdateDto;
import com.rms.rms.dto.user.*;
import com.rms.rms.entity.Schedule;
import com.rms.rms.entity.User;
import com.rms.rms.enums.Role;
import com.rms.rms.exception.ScheduleException;
import com.rms.rms.exception.UserException;
import com.rms.rms.mapper.ScheduleMapper;
import com.rms.rms.repository.OrderRepository;
import com.rms.rms.repository.ScheduleRepository;
import com.rms.rms.repository.UserRepository;
import com.rms.rms.security.entity.AuthenticationRequest;
import com.rms.rms.security.entity.AuthenticationResponse;
import com.rms.rms.security.util.JWTUtil;
import com.rms.rms.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class UserServiceImpl extends BaseServiceImpl<UserCreateDto, UserUpdateDto, UserResponseDto, User> implements UserService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private ScheduleMapper scheduleMapper;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto save(UserCreateDto entity) {
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return super.save(entity);
    }

    @Override
    public UserResponseDto update(Long id, UserUpdateDto user) {
        User userToBeUpdated = getUserById(id);
        updateFields(user, userToBeUpdated);
        return baseMapper.entityToResponseDto(jpaRepository.save(userToBeUpdated));
    }

    @Override
    public void delete(Long id) {
        User userWithId = getUserById(id);
        if(!userWithId.getActive()) {
            throw new UserException("User with id: " + id + " is inactive!");
        }

        if(userWithId.getRole().equals(Role.ADMIN)) {
            throw new UserException("User with role admin cannot be deleted!");
        }

        if(userWithId.getRole().equals(Role.OPERATOR)) {
            if(!orderRepository.getOrdersOfOperator(userWithId).isEmpty()) {
                throw new UserException("User with id: " + id + " and role: " + Role.OPERATOR + " cannot be deleted!");
            }
        }

        if (userWithId.getRole().equals(Role.DELIVERY)) {
            if(!orderRepository.getDeliveriesOfDeliveryGuyAfter(userWithId).isEmpty()) {
                throw new UserException("User with id: " + id + " and role: " + Role.DELIVERY + " cannot be deleted!");
            }

        }

        userWithId.setActive(Boolean.FALSE);
        setUpdatedAtField(userWithId);
        jpaRepository.save(userWithId);
    }

    @Override
    public void updatePassword(Long id, UserUpdatePasswordDto password) {
        User userWithId = getUserById(id);
        userWithId.setPassword(password.getPassword());
        setUpdatedAtField(userWithId);
        jpaRepository.save(userWithId);
    }

    @Override
    public ScheduleResponseDto addScheduleToDeliveryGuy(Long id, ScheduleCreateDto schedule) {

        if(isNotValidSchedule(schedule)) {
            throw new ScheduleException("Please enter a valid schedule!");
        }

        return scheduleMapper.entityToResponseDto(jpaRepository.findById(id)
                .map(user -> {
                    if(!user.getRole().equals(Role.DELIVERY)) {
                        throw new UserException("User with id: " + id + " does not have a role of DELIVERY!");
                    }
                    Schedule scheduleEntity = scheduleMapper.createDtoToEntity(schedule);
                    scheduleEntity.setUser(user);
                    return scheduleRepository.save(scheduleEntity);
                })
                .orElseThrow(() -> new UserException("User with id: " + id + " does not exist!"))
        );
    }

    private boolean isNotValidSchedule(ScheduleCreateDto schedule) {
        return schedule.getEndWorkHour().isBefore(schedule.getStartWorkHour()) || schedule.getWorkDate().getDayOfMonth() != schedule.getStartWorkHour().getDayOfMonth() || schedule.getWorkDate().getMonth() != schedule.getStartWorkHour().getMonth();
    }

    @Override
    public ScheduleResponseDto updateScheduleOfDeliveryGuy(Long scheduleId, ScheduleUpdateDto schedule) {

        if(schedule.getEndWorkHour().isBefore(schedule.getStartWorkHour())) {
            throw new ScheduleException("Please enter a valid exception!");
        }

        return scheduleRepository.findById(scheduleId)
                .map(value -> scheduleMapper.entityToResponseDto(scheduleRepository.save(scheduleMapper.getUpdatedSchedule(value, schedule))))
                .orElseThrow(() -> new ScheduleException("Schedule with id: " + scheduleId + " does not exist!"));
    }

    @Override
    public List<UserResponseDto> findTopCustomers(Integer n) {
        return userRepository.findTopCustomers(n)
                .stream()
                .map(id -> baseMapper.entityToResponseDto(jpaRepository.getOne(id)))
                .collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByUserName(username)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getUserName(),
                        user.getPassword(),
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().getRole()))
                ))
                .orElseThrow(() -> new BadCredentialsException("Bad credentials!"));

    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()
            ));
            UserDetails userDetails = loadUserByUsername(authenticationRequest.getUsername());
            String token = jwtUtil.generateToken(userDetails);

            Role role = null;

            Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();
            if (roles.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
                role = Role.ADMIN;
            }
            if (roles.contains(new SimpleGrantedAuthority("ROLE_OPERATOR"))) {
                role = Role.OPERATOR;
            }
            if (roles.contains(new SimpleGrantedAuthority("ROLE_DELIVERY"))) {
                role = Role.DELIVERY;
            }

            return new AuthenticationResponse(token, role);
        }catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

    }

    private User getUserById(Long id) {
        return jpaRepository.findById(id)
                .orElseThrow(() -> new UserException("User with id: " + id + " does not exist!"));
    }

    public void updateFields(UserUpdateDto user, User userToBeUpdated) {
        userToBeUpdated.setFirstName(user.getFirstName());
        userToBeUpdated.setLastName(user.getLastName());
        userToBeUpdated.setUserName(user.getUserName());
        userToBeUpdated.setEmail(user.getEmail());
        userToBeUpdated.setTelephoneNumber(user.getTelephoneNumber());
        userToBeUpdated.setAddress(user.getAddress());
        setUpdatedAtField(userToBeUpdated);
    }

    private void setUpdatedAtField(User userWithId) {
        userWithId.setUpdatedAt(LocalDate.now());
    }

}
