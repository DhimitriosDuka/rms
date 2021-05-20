package com.rms.rms.mapper;

import com.rms.rms.config.ModelMapperBean;
import com.rms.rms.dto.ingredient.IngredientCreateDto;
import com.rms.rms.dto.ingredient.IngredientResponseDto;
import com.rms.rms.dto.ingredient.IngredientUpdateDto;
import com.rms.rms.entity.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Setter
@Component
public class IngredientMapper {

    private final ModelMapper modelMapper;

    public Ingredient createDtoToEntity(IngredientCreateDto ingredient) {
        return modelMapper.map(ingredient, Ingredient.class);
    }

    public IngredientResponseDto entityToResponseDto(Ingredient ingredient) {
        return modelMapper.map(ingredient, IngredientResponseDto.class);
    }

    public Ingredient updateDtoToEntity(IngredientUpdateDto ingredient) {
        return modelMapper.map(ingredient, Ingredient.class);
    }

}
