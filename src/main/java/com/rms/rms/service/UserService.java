package com.rms.rms.service;

import com.rms.rms.dto.schedule.ScheduleCreateDto;
import com.rms.rms.dto.schedule.ScheduleResponseDto;
import com.rms.rms.dto.schedule.ScheduleUpdateDto;
import com.rms.rms.dto.user.*;
import com.rms.rms.entity.User;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface UserService extends BaseService<UserCreateDto, UserUpdateDto, UserResponseDto, User>{

    UserResponseDto update(Long id, UserUpdateDto updateDto);
    void delete(@NotNull Long id);
    void updatePassword(@NotNull Long id, @Valid UserUpdatePasswordDto password);
    ScheduleResponseDto addScheduleToDeliveryGuy(Long id, ScheduleCreateDto schedule);
    ScheduleResponseDto updateScheduleOfDeliveryGuy(Long userId, ScheduleUpdateDto schedule);


}
