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
import com.rms.rms.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Validated
public class UserServiceImpl extends BaseServiceImpl<UserCreateDto, UserUpdateDto, UserResponseDto, User> implements UserService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

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
    }

    @Override
    public void updatePassword(Long id, UserUpdatePasswordDto password) {
        User userWithId = getUserById(id);
        userWithId.setPassword(password.getPassword());
        setUpdatedAtField(userWithId);
    }

    @Override
    public ScheduleResponseDto addScheduleToDeliveryGuy(Long id, ScheduleCreateDto schedule) {
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

    @Override
    public ScheduleResponseDto updateScheduleOfDeliveryGuy(Long scheduleId, ScheduleUpdateDto schedule) {
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
