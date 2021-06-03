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
import java.util.List;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = {"userName", "email"}))
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

    private Boolean active = Boolean.TRUE;

    @Column(updatable = false)
    private LocalDate createdAt = LocalDate.now();

    private LocalDate updatedAt;

    @OneToMany(mappedBy = "costumer")
    private List<Order> costumerOrders;

    @OneToMany(mappedBy = "deliveryGuy")
    private List<Order> deliveryGuyOrders;

    @OneToMany(mappedBy = "user")
    private List<Schedule> schedules;

}
