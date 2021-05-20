package com.rms.rms.dto.user;


import com.rms.rms.enums.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponseDto {

    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String telephoneNumber;
    private String address;
    private Boolean active;
    private Role role;

}
