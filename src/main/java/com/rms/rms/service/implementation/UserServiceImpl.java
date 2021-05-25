package com.rms.rms.service.implementation;

import com.rms.rms.dto.user.*;
import com.rms.rms.entity.User;
import com.rms.rms.exception.UserException;
import com.rms.rms.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@AllArgsConstructor
@Service
@Validated
public class UserServiceImpl extends BaseServiceImpl<UserCreateDto, UserUpdateDto, UserResponseDto, User> implements UserService {

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
        userWithId.setActive(Boolean.FALSE);
        setUpdatedAtField(userWithId);
    }

    @Override
    public UserResponseDto updateRole(Long id, UserRoleUpdateDto role) {
        User userWithId = getUserById(id);
        userWithId.setRole(role.getRole());
        setUpdatedAtField(userWithId);
        return baseMapper.entityToResponseDto(jpaRepository.save(userWithId));
    }

    @Override
    public void updatePassword(Long id, UserUpdatePasswordDto password) {
        User userWithId = getUserById(id);
        userWithId.setPassword(password.getPassword());
        setUpdatedAtField(userWithId);
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
        return jpaRepository.findById(id)
                .orElseThrow(() -> new UserException("User with id: " + id + " does not exist!"));
    }

    private void setUpdatedAtField(User userWithId) {
        userWithId.setUpdatedAt(LocalDate.now());
    }

}
