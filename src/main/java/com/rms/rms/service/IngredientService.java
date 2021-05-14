package com.rms.rms.service;

import com.rms.rms.dto.ingredient.IngredientCreateDto;
import com.rms.rms.dto.ingredient.IngredientResponseDto;
import com.rms.rms.dto.ingredient.IngredientUpdateDto;
import com.rms.rms.entity.Ingredient;

import java.util.List;

public interface IngredientService {

    IngredientResponseDto save(IngredientCreateDto ingredient);
    List<IngredientResponseDto> findAll();
    IngredientResponseDto findById(Long id);
    IngredientResponseDto update(Long id, IngredientUpdateDto ingredient);

}
