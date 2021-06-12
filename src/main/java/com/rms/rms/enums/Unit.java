package com.rms.rms.enums;

import lombok.AllArgsConstructor;

import java.util.Random;

@AllArgsConstructor
public enum Unit {

    ML("ml"),
    GRAM("gram"),
    OUNCE("ounce"),
    CUP("cup");

    private final String unit;

    public static Unit getRandomUnit() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }


}
