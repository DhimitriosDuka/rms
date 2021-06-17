package com.rms.rms.dto.user;

import com.rms.rms.annotations.Password;
import com.rms.rms.annotations.PhoneNumber;
import com.rms.rms.enums.Role;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class UserCreateDto {

    @NotBlank(message = "Firstname must not be blank!")
    private String firstName;

    @NotBlank(message = "Lastname must not be blank!")
    private String lastName;

    @NotBlank(message = "Username must not be blank!")
    private String userName;

    @Email(message = "Please provide a valid email!")
    private String email;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Password must contain at minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character!")
    private String password;

    @Length(min = 10, max = 13)
    @Pattern(regexp = "(0|(\\+|00)355)6([789])[0-9]{7,}", message = "Please enter a valid phone number!")
    private String telephoneNumber;

    @NotBlank(message = "Address must not be blank")
    private String address;

    @NotNull(message = "Role must not be null.")
    private Role role;

}
