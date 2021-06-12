package com.rms.rms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {

    ADMIN("ADMIN"),
    OPERATOR("OPERATOR"),
    DELIVERY("DELIVERY");

    private final String role;

}
