package com.rms.rms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Random;

@AllArgsConstructor
@Getter
public enum Category {

    VEGAN("Vegan"),
    NON_VEGAN("Non vengan"),
    DAIRY("Dairy"),
    DAIRY_FREE("Dairy free");

    private final String category;

    public static Category getRandomCategory() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }

}
