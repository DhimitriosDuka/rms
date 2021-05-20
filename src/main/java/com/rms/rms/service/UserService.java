package com.rms.rms.service;

import com.rms.rms.dto.user.*;
import com.rms.rms.enums.Role;

import java.util.List;

public interface UserService {

    UserResponseDto save(UserCreateDto user);
    List<UserResponseDto> findAll();
    UserResponseDto update(Long id, UserUpdateDto user);
    UserResponseDto findById(Long id);
    void delete(Long id);
    UserResponseDto updateRole(Long id, UserRoleUpdateDto role);
    void updatePassword(Long id, UserUpdatePasswordDto password);

}
