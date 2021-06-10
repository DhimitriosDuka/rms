package com.rms.rms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Random;

@AllArgsConstructor
@Getter
public enum Type {

    DISH("Dish"),
    BEVERAGE("Beverage"),
    SANDWICH("Sandwich");

    private final String type;

    public static Type getRandomType() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }

}
