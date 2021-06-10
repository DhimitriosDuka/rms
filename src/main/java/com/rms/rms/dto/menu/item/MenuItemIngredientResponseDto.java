package com.rms.rms.dto.menu.item;

import com.rms.rms.entity.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemIngredientResponseDto {

    private Ingredient ingredient;
    private Integer amount;

}
