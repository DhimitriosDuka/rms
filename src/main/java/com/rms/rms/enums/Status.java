package com.rms.rms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Status {

    ONGOING("The order is being processed."),
    DELIVERING("The order is being delivered."),
    DELIVERED("The order has been delivered."),
    CANCELLED("The order has been canceled.");

    private final String status;

}
