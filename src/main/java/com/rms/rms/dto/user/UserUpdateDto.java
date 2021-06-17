package com.rms.rms.dto.user;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class UserUpdateDto {

    @NotBlank(message = "Firstname must not be blank!")
    private String firstName;

    @NotBlank(message = "Lastname must not be blank!")
    private String lastName;

    @NotBlank(message = "Username must not be blank!")
    private String userName;

    @Email(message = "Please provide a valid email!")
    private String email;

    @Length(min = 10, max = 13)
    @Pattern(regexp = "(0|(\\+|00)355)6([789])[0-9]{7,}", message = "Please enter a valid phone number!")
    private String telephoneNumber;

    @NotBlank(message = "Address must not be blank")
    private String address;

}
