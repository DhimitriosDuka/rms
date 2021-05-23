package com.rms.rms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {

    ADMIN("Admin"),
    OPERATOR("Operator"),
    DELIVERY("Delivery");

    private final String role;

}
