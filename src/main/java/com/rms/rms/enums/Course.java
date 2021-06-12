package com.rms.rms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Random;

@AllArgsConstructor
@Getter
public enum Course {

    HORS_DOEUVRES("Hors-d'oeuvres"),
    AMUSE_BOUCHE("Amuse-bouche"),
    SOUP("Soup"),
    APPETIZER("Appetizer"),
    SALAD("Salad"),
    FISH("Fish"),
    FIRST_MAIN_DISH("First main dish"),
    PALATE_CLEANSER_COURSE("Palate cleanser course"),
    SECOND_MAIN_DISH("Second main dish"),
    CHEESE_PLATE("Cheese plate"),
    DESSERT("Dessert"),
    POST_MEAL_DRINKS_AND_PASTRIES("Post-meal drinks and pastries");

    private final String course;

    public static Course getRandomCourse() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }

}
