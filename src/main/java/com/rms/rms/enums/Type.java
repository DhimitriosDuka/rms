package com.rms.rms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Type {

    DISH("Dish"),
    BEVERAGE("Beverage"),
    SANDWICH("Sandwich");

    private final String type;

}
