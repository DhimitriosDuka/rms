package com.rms.rms.service.implementation;

import com.rms.rms.entity.Ingredient;
import com.rms.rms.exception.IngredientException;
import com.rms.rms.exception.NullParameterException;
import com.rms.rms.repository.IngredientsRepository;
import com.rms.rms.service.IngredientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private final IngredientsRepository ingredientsRepository;

    @Override
    public Ingredient save(Ingredient ingredient) {
        checkNullabilityOfParameters(ingredient);
        ingredientsRepository.findByName(ingredient.getName())
                .ifPresent(value -> {
                    throw new IngredientException("Ingredient with name: " + value.getName() + " already exists!");
                });
        return ingredientsRepository.save(ingredient);
    }

    @Override
    public List<Ingredient> findAll() {
        return ingredientsRepository.findAll();
    }

    @Override
    public Ingredient findById(Long id) {
        checkNullabilityOfParameters(id);
        return Optional.of(ingredientsRepository.findById(id))
                    .get()
                    .orElseThrow(() -> new IngredientException("Ingredient with id: " + id + " does not exists!"));

    }

    @Override
    public Ingredient update(Long id, Ingredient ingredient) {

        checkNullabilityOfParameters(id, ingredient);

        if(doesNotExistsById(id)) {
            throw new IngredientException("Ingredient with id: " + id + " does not exists!");
        }

        Optional<Ingredient> existingIngredientWithName = ingredientsRepository.findByName(ingredient.getName());
        if(existingIngredientWithName.isPresent()) {
            Ingredient ingredientOptionalValue = existingIngredientWithName.get();
            if(!ingredientOptionalValue.getId().equals(id)) {
                throw new IngredientException("Ingredient with name: " + ingredientOptionalValue.getName() + " already exists!");
            }
        }
        ingredient.setId(id);
        ingredient.setUpdatedAt(LocalDateTime.now());
        return ingredientsRepository.save(ingredient);
    }

    private boolean doesNotExistsById(Long id) {
        return !ingredientsRepository.existsById(id);
    }

    private void checkNullabilityOfParameters(Object... objects) {
        for (Object o : objects) {
            if(Objects.isNull(o)) throw new NullParameterException("Parameter/s must not be null!");
        }
    }
}
