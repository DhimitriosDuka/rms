package com.rms.rms.mapper;

import com.rms.rms.config.ModelMapperBean;
import com.rms.rms.dto.ingredient.IngredientCreateDto;
import com.rms.rms.dto.ingredient.IngredientResponseDto;
import com.rms.rms.dto.ingredient.IngredientUpdateDto;
import com.rms.rms.entity.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Setter
@Component
public class IngredientMapper {

    private final ModelMapperBean modelMapperBean;

    public Ingredient createDtoToEntity(IngredientCreateDto ingredient) {
        return modelMapperBean.modelMapper().map(ingredient, Ingredient.class);
    }

    public IngredientResponseDto entityToResponseDto(Ingredient ingredient) {
        return modelMapperBean.modelMapper().map(ingredient, IngredientResponseDto.class);
    }

    public Ingredient updateDtoToEntity(IngredientUpdateDto ingredient) {
        return modelMapperBean.modelMapper().map(ingredient, Ingredient.class);
    }

}
