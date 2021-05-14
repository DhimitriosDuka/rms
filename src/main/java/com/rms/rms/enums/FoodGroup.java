package com.rms.rms.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum FoodGroup {

    FRUITS("fruits"),
    VEGETABLE("vegetable"),
    GRAIN("grain"),
    PROTEIN("protein"),
    DAIRY("dairy");

    private final String foodGroup;

}
