package com.rms.rms.service.implementation;

import com.rms.rms.dto.user.*;
import com.rms.rms.entity.User;
import com.rms.rms.exception.UserException;
import com.rms.rms.mapper.UserMapper;
import com.rms.rms.repository.UserRepository;
import com.rms.rms.service.SuperService;
import com.rms.rms.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto save(@Valid UserCreateDto user) {
        userRepository.findByUserName(user.getUserName())
                .ifPresent(value -> {
                    throw new UserException("User with username: " + user.getUserName() + " already exists!");
                });
        userRepository.findByEmail(user.getEmail())
                .ifPresent(value -> {
                    throw new UserException("User with email: " + user.getEmail() + " already exists!");
                });
        User userEntity = userMapper.createDtoToEntity(user);
        userEntity.setCreatedAt(LocalDate.now());
        userEntity.setActive(Boolean.TRUE);
        return userMapper.entityToResponseDto(userRepository.save(userEntity));
    }

    @Override
    public List<UserResponseDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDto update(@NotNull Long id, @Valid UserUpdateDto user) {
        User userToBeUpdated = getUserById(id);

        Optional<User> userWithUsername = userRepository.findByUserName(user.getUserName());
        if(userWithUsername.isPresent() && !userWithUsername.get().getId().equals(id)) {
            throw new UserException("User with username: " + user.getUserName() + " already exists!");
        }

        Optional<User> userWithEmail = userRepository.findByEmail(user.getEmail());
        if(userWithEmail.isPresent() && !userWithEmail.get().getId().equals(id)) {
            throw new UserException("User with email: " + user.getEmail() + " already exists!");
        }
        updateFields(user, userToBeUpdated);

        return userMapper.entityToResponseDto(userRepository.save(userToBeUpdated));

    }

    @Override
    public UserResponseDto findById(@NotNull Long id) {
        return userRepository.findById(id)
                .map(userMapper::entityToResponseDto)
                .orElseThrow(() -> new UserException("User with id: " + id + " does not exist!"));
    }

    @Override
    public void delete(@NotNull Long id) {
        User userWithId = getUserById(id);
        if(!userWithId.getActive()) {
            throw new UserException("User with id: " + id + " is inactive!");
        }
        userWithId.setActive(Boolean.FALSE);
        setUpdatedAtField(userWithId);
        userRepository.save(userWithId);
    }

    @Override
    public UserResponseDto updateRole(@NotNull Long id, @Valid UserRoleUpdateDto role) {
        User userWithId = getUserById(id);

        if(userWithId.getRole().equals(role.getRole())) {
            throw new UserException("User with id: " + id + " already has a role of:" + role.getRole());
        }
        userWithId.setRole(role.getRole());
        setUpdatedAtField(userWithId);
        return userMapper.entityToResponseDto(userRepository.save(userWithId));
    }

    @Override
    public void updatePassword(@NotNull Long id, @Valid UserUpdatePasswordDto password) {
        User userWithId = getUserById(id);
        userWithId.setPassword(password.getPassword());
        setUpdatedAtField(userWithId);
        userRepository.save(userWithId);

    }

    private void updateFields(UserUpdateDto user, User userToBeUpdated) {
        userToBeUpdated.setFirstName(user.getFirstName());
        userToBeUpdated.setLastName(user.getLastName());
        userToBeUpdated.setUserName(user.getUserName());
        userToBeUpdated.setEmail(user.getEmail());
        userToBeUpdated.setTelephoneNumber(user.getTelephoneNumber());
        userToBeUpdated.setAddress(user.getAddress());
        setUpdatedAtField(userToBeUpdated);
    }

    private User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserException("User with id: " + id + " does not exist!"));
    }

    private void setUpdatedAtField(User userWithId) {
        userWithId.setUpdatedAt(LocalDate.now());
    }

}
