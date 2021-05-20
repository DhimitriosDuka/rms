package com.rms.rms.mapper;

import com.rms.rms.dto.user.UserCreateDto;
import com.rms.rms.dto.user.UserResponseDto;
import com.rms.rms.dto.user.UserUpdateDto;
import com.rms.rms.entity.User;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Setter
@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    public User createDtoToEntity(UserCreateDto user) {
        return modelMapper.map(user, User.class);
    }

    public UserResponseDto entityToResponseDto(User user) {
        return modelMapper.map(user, UserResponseDto.class);
    }

    public User updateDtoToEntity(UserUpdateDto update) {
        return modelMapper.map(update, User.class);
    }

}
