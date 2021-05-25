package com.rms.rms.service;

import com.rms.rms.dto.user.*;
import com.rms.rms.entity.User;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface UserService extends BaseService<UserCreateDto, UserUpdateDto, UserResponseDto, User>{

    UserResponseDto update(Long id, UserUpdateDto updateDto);
    void delete(@NotNull Long id);
    UserResponseDto updateRole(@NotNull Long id, @Valid UserRoleUpdateDto role);
    void updatePassword(@NotNull Long id, @Valid UserUpdatePasswordDto password);

}
