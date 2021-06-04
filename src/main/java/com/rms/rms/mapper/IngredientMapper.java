package com.rms.rms.mapper;

import com.rms.rms.dto.ingredient.IngredientCreateDto;
import com.rms.rms.dto.ingredient.IngredientResponseDto;
import com.rms.rms.dto.ingredient.IngredientUpdateDto;
import com.rms.rms.entity.Ingredient;
import org.springframework.stereotype.Component;

@Component
public class IngredientMapper extends BaseMapper<IngredientCreateDto, IngredientUpdateDto, IngredientResponseDto, Ingredient>{

    public IngredientMapper() {
        super(IngredientResponseDto.class, Ingredient.class);
    }

}
