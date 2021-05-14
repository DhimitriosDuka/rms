package com.rms.rms.dto.ingredient;

import com.rms.rms.enums.FoodGroup;
import com.rms.rms.enums.Unit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class IngredientCreateDto {

    private String name;
    private Double calories;
    private Double amount;
    private Unit unit;
    private Double fat;
    private Double cholesterol;
    private Double sodium;
    private Double protein;
    private Double sugar;
    private FoodGroup foodGroup;

}
