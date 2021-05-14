package com.rms.rms.service;

import com.rms.rms.entity.Ingredient;

import java.util.List;

public interface IngredientService {

    Ingredient save(Ingredient ingredient);
    List<Ingredient> findAll();
    Ingredient findById(Long id);
    Ingredient update(Long id, Ingredient ingredient);

}
