package com.rms.rms.dto.user;

import lombok.Data;

@Data
public class UserUpdateDto {

    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String telephoneNumber;
    private String address;

}
