package com.rms.rms.dto.user;

import com.rms.rms.annotations.PhoneNumber;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserUpdateDto {

    @NotBlank(message = "Firstname must not be blank!")
    private String firstName;

    @NotBlank(message = "Lastname must not be blank!")
    private String lastName;

    @NotBlank(message = "Username must not be blank!")
    private String userName;

    @Email(message = "Please provide a valid email!")
    private String email;

    @PhoneNumber
    private String telephoneNumber;

    @NotBlank(message = "Address must not be blank")
    private String address;

}
