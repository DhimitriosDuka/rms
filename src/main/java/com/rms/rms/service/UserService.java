package com.rms.rms.service;

import com.rms.rms.dto.user.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface UserService {

    UserResponseDto save(@Valid UserCreateDto user);
    List<UserResponseDto> findAll();
    UserResponseDto update(@NotNull Long id, @Valid UserUpdateDto user);
    UserResponseDto findById(@NotNull Long id);
    void delete(@NotNull Long id);
    UserResponseDto updateRole(@NotNull Long id, @Valid UserRoleUpdateDto role);
    void updatePassword(@NotNull Long id, @Valid UserUpdatePasswordDto password);

}
