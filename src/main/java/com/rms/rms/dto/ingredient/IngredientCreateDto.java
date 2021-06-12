package com.rms.rms.dto.ingredient;

import com.rms.rms.enums.FoodGroup;
import com.rms.rms.enums.Unit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IngredientCreateDto {

    @NotNull(message = "Name may not be null!")
    @Pattern(regexp = "^\\w+( \\w+)*$", message = "Name must contain only alphabetical characters!")
    private String name;

    @NotNull(message = "Calories may not be null!")
    @Min(value = 0, message = "Value of Calories must not be less than 0!")
    private Double calories;

    @NotNull(message = "Amount may not be null!")
    @Min(value = 0, message = "Value of Amount must not be negative!")
    private Double amount;

    @NotNull(message = "Unit may not be null!")
    private Unit unit;

    @NotNull(message = "Fat may not be null!")
    @Range(min = 0, max = 100, message = "Value of Fat must be between 0 and 100!")
    private Double fat;

    @NotNull(message = "Cholesterol may not be null!")
    @Range(min = 0, max = 100, message = "Value of Cholesterol must be between 0 and 100!")
    private Double cholesterol;

    @NotNull(message = "Sodium may not be null!")
    @Range(min = 0, max = 100, message = "Value of Sodium must be between 0 and 100!")
    private Double sodium;

    @NotNull(message = "Protein may not be null!")
    @Range(min = 0, max = 100, message = "Value of Protein must be between 0 and 100!")
    private Double protein;

    @NotNull(message = "Sugar may not be null!")
    @Range(min = 0, max = 100, message = "Value of Sugar must be between 0 and 100!")
    private Double sugar;

    @NotNull(message = "Food group may not be null!")
    private FoodGroup foodGroup;

}
