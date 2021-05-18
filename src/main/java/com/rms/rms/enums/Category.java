package com.rms.rms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Category {

    VEGAN("Vegan"),
    NON_VEGAN("Non vengan"),
    DAIRY("Dairy"),
    DAIRY_FREE("Dairy free");

    private final String category;

}
