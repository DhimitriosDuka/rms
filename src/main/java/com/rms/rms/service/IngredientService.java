package com.rms.rms.service;

import com.rms.rms.dto.ingredient.IngredientCreateDto;
import com.rms.rms.dto.ingredient.IngredientResponseDto;
import com.rms.rms.dto.ingredient.IngredientUpdateDto;
import com.rms.rms.entity.Ingredient;
import com.rms.rms.filters.IngredientFilter;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface IngredientService extends BaseService<IngredientCreateDto, IngredientUpdateDto, IngredientResponseDto, Ingredient> {

    IngredientResponseDto update(Long id, IngredientUpdateDto updateDto);
    List<IngredientResponseDto> findTopIngredients(Integer n);
    List<IngredientResponseDto> findAllByFilter(IngredientFilter ingredientFilter);


}
