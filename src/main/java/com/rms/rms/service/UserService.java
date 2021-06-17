package com.rms.rms.service;

import com.rms.rms.dto.schedule.ScheduleCreateDto;
import com.rms.rms.dto.schedule.ScheduleResponseDto;
import com.rms.rms.dto.schedule.ScheduleUpdateDto;
import com.rms.rms.dto.user.*;
import com.rms.rms.entity.User;
import com.rms.rms.security.entity.AuthenticationRequest;
import com.rms.rms.security.entity.AuthenticationResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface UserService extends BaseService<UserCreateDto, UserUpdateDto, UserResponseDto, User>, UserDetailsService {

    UserResponseDto update(Long id, UserUpdateDto updateDto);
    void delete(Long id);
    void updatePassword(Long id, UserUpdatePasswordDto password);
    ScheduleResponseDto addScheduleToDeliveryGuy(Long id, ScheduleCreateDto schedule);
    ScheduleResponseDto updateScheduleOfDeliveryGuy(Long userId, ScheduleUpdateDto schedule);
    List<UserResponseDto> findTopCustomers(Integer n);
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) throws Exception;

}
