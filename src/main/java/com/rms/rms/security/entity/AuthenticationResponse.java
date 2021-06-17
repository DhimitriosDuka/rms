package com.rms.rms.security.entity;

import com.rms.rms.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponse {

    private String token;
    private Role role;

}
