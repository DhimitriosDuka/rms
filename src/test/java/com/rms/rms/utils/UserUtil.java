package com.rms.rms.utils;

import com.rms.rms.dto.user.UserResponseDto;
import com.rms.rms.dto.user.UserUpdateDto;
import com.rms.rms.entity.User;
import com.rms.rms.enums.Role;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserUtil {

    public User initEntity(Long id, Role role) {

        User user = new User();
        user.setId(id);
        user.setUserName("Username" + id);
        user.setActive(Boolean.TRUE);
        user.setUpdatedAt(LocalDate.now());
        user.setPassword("Password" + id);
        user.setAddress("Address" + id);
        user.setEmail("email@email.com");
        user.setRole(role);
        user.setTelephoneNumber("0675401811");
        user.setFirstName("FirstName" + id);
        user.setLastName("LastName" + id);
        user.setCreatedAt(LocalDate.now());
        return user;
    }

    public UserUpdateDto convertToUserUpdateDto(User user) {
        UserUpdateDto userUpdateDto = new UserUpdateDto();
        userUpdateDto.setUserName(user.getUserName() + " New");
        userUpdateDto.setAddress(user.getAddress());
        userUpdateDto.setEmail(user.getEmail());
        userUpdateDto.setTelephoneNumber(user.getTelephoneNumber());
        userUpdateDto.setFirstName(user.getFirstName());
        userUpdateDto.setLastName(user.getLastName());
        return userUpdateDto;
    }

    public UserResponseDto convertToResponseDto(User user) {

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setUserName(user.getUserName());
        userResponseDto.setAddress(user.getAddress());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setRole(user.getRole());
        userResponseDto.setTelephoneNumber(user.getTelephoneNumber());
        userResponseDto.setFirstName(user.getFirstName());
        userResponseDto.setLastName(user.getLastName());
        return userResponseDto;

    }

    public List<User> initDeliveryGuys() {
        List<User> users = new ArrayList<>();
        for(int i = 0; i< 4; i++) {
            users.add(initEntity((long) i, Role.DELIVERY));
        }
        return users;
    }

}
