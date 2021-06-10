package com.rms.rms.enums;

import lombok.AllArgsConstructor;

import java.util.Random;

@AllArgsConstructor
public enum FoodGroup {

    FRUITS("fruits"),
    VEGETABLE("vegetable"),
    GRAIN("grain"),
    PROTEIN("protein"),
    DAIRY("dairy");

    private final String foodGroup;

    public static FoodGroup getRandomFoodGroup() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }

}
