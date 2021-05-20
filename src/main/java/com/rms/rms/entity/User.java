package com.rms.rms.entity;

import com.rms.rms.annotations.Password;
import com.rms.rms.annotations.PhoneNumber;
import com.rms.rms.enums.Role;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Firstname must not be blank!")
    private String firstName;

    @NotBlank(message = "Lastname must not be blank!")
    private String lastName;

    @NotBlank(message = "Username must not be blank!")
    private String userName;

    @Email
    private String email;

    @Password
    private String password;

    @NotBlank(message = "Phone number must not be blank!")
    @PhoneNumber
    private String telephoneNumber;

    @NotBlank(message = "Address must not be blank")
    private String address;

    @NotNull(message = "Role must not be null.")
    private Role role;

    private Boolean active;

    private LocalDate createdAt;

    private LocalDate updatedAt;


}
