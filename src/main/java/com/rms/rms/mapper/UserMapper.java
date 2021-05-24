package com.rms.rms.mapper;

import com.rms.rms.dto.user.UserCreateDto;
import com.rms.rms.dto.user.UserResponseDto;
import com.rms.rms.dto.user.UserUpdateDto;
import com.rms.rms.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends BaseMapper<UserCreateDto, UserUpdateDto, UserResponseDto, User>{

    public UserMapper() {
        super(UserResponseDto.class, User.class);
    }

}
