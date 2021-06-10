package com.rms.rms.filters;

import com.rms.rms.enums.FoodGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class IngredientFilter {

    private String name;
    private Double calories;
    private Double fat;
    private Double cholesterol;
    private FoodGroup foodGroup;


}
