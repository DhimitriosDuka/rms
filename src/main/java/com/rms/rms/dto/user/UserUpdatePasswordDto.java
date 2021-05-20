package com.rms.rms.dto.user;

import com.rms.rms.annotations.Password;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserUpdatePasswordDto {

    @Password
    private String password;

}
