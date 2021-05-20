package com.rms.rms.dto.user;

import com.rms.rms.annotations.Password;
import com.rms.rms.annotations.PhoneNumber;
import com.rms.rms.enums.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class UserCreateDto {

    @NotBlank(message = "Firstname must not be blank!")
    private String firstName;

    @NotBlank(message = "Lastname must not be blank!")
    private String lastName;

    @NotBlank(message = "Username must not be blank!")
    private String userName;

    @Email(message = "Please provide a valid email!")
    private String email;

    @Password
    private String password;

    @PhoneNumber
    private String telephoneNumber;

    @NotBlank(message = "Address must not be blank")
    private String address;

    @NotNull(message = "Role must not be null.")
    private Role role;

}
