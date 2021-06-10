package com.rms.rms.utils;

import com.rms.rms.dto.ingredient.IngredientCreateDto;
import com.rms.rms.dto.ingredient.IngredientResponseDto;
import com.rms.rms.dto.ingredient.IngredientUpdateDto;
import com.rms.rms.entity.Ingredient;
import com.rms.rms.enums.FoodGroup;
import com.rms.rms.enums.Unit;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IngredientUtil {

    public Ingredient initEntity(Long id) {

        Random random = new Random();
        Ingredient ingredient = new Ingredient();
        ingredient.setId(id);
        ingredient.setName("Ingredient" + id);
        ingredient.setFoodGroup(FoodGroup.getRandomFoodGroup());
        ingredient.setSugar(random.nextDouble());
        ingredient.setProtein(random.nextDouble());
        ingredient.setSodium(random.nextDouble());
        ingredient.setCholesterol(random.nextDouble());
        ingredient.setFat(random.nextDouble());
        ingredient.setUnit(Unit.getRandomUnit());
        ingredient.setCalories(random.nextDouble());
        return ingredient;

    }

    public List<Ingredient> initEntityList(Integer n) {
        List<Ingredient> ingredients = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            ingredients.add(initEntity((long) i));
        }
        return ingredients;
    }

    public IngredientResponseDto covertToResponseDto(Ingredient entity) {

        IngredientResponseDto ingredient = new IngredientResponseDto();
        ingredient.setId(entity.getId());
        ingredient.setName(entity.getName());
        ingredient.setFoodGroup(entity.getFoodGroup());
        ingredient.setSugar(entity.getSugar());
        ingredient.setProtein(entity.getProtein());
        ingredient.setSodium(entity.getSodium());
        ingredient.setCholesterol(entity.getCholesterol());
        ingredient.setFat(entity.getFat());
        ingredient.setUnit(entity.getUnit());
        ingredient.setCalories(entity.getCalories());

        return ingredient;

    }

    public IngredientCreateDto convertEntityToCreateDto(Ingredient entity) {
        IngredientCreateDto ingredient = new IngredientCreateDto();
        ingredient.setName(entity.getName());
        ingredient.setFoodGroup(entity.getFoodGroup());
        ingredient.setSugar(entity.getSugar());
        ingredient.setProtein(entity.getProtein());
        ingredient.setSodium(entity.getSodium());
        ingredient.setCholesterol(entity.getCholesterol());
        ingredient.setFat(entity.getFat());
        ingredient.setUnit(entity.getUnit());
        ingredient.setCalories(entity.getCalories());

        return ingredient;
    }

    public IngredientUpdateDto convertEntityToUpdateDto(Ingredient entity) {
        IngredientUpdateDto ingredient = new IngredientUpdateDto();
        ingredient.setName(entity.getName());
        ingredient.setFoodGroup(entity.getFoodGroup());
        ingredient.setSugar(entity.getSugar());
        ingredient.setProtein(entity.getProtein());
        ingredient.setSodium(entity.getSodium());
        ingredient.setCholesterol(entity.getCholesterol());
        ingredient.setFat(entity.getFat());
        ingredient.setUnit(entity.getUnit());
        ingredient.setCalories(entity.getCalories());

        return ingredient;
    }


}
