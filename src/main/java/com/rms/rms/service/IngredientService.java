package com.rms.rms.service;

import com.rms.rms.dto.ingredient.IngredientCreateDto;
import com.rms.rms.dto.ingredient.IngredientResponseDto;
import com.rms.rms.dto.ingredient.IngredientUpdateDto;
import com.rms.rms.entity.Ingredient;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface IngredientService {

    IngredientResponseDto save(@Valid IngredientCreateDto ingredient);
    List<IngredientResponseDto> findAll();
    IngredientResponseDto findById(@NotNull Long id);
    IngredientResponseDto update(@NotNull Long id, @Valid IngredientUpdateDto ingredient);


}
