package com.rms.rms.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Unit {

    ML("ml"),
    GRAM("gram"),
    OUNCE("ounce"),
    CUP("cup");

    private final String unit;

}
