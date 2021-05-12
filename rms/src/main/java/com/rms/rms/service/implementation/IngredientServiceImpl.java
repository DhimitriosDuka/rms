package com.rms.rms.service.implementation;

import com.rms.rms.entity.Ingredient;
import com.rms.rms.exception.IngredientException;
import com.rms.rms.exception.NullParameterException;
import com.rms.rms.repository.IngredientsRepository;
import com.rms.rms.service.IngredientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
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
                    if (Objects.isNull(ingredient.getId()) || !ingredient.getId().equals(value.getId())){
                        throw new IngredientException("Ingredient with name: " + value.getName() + " already exists!");
                    }
                });
        ingredient.setUpdatedAt(LocalDateTime.now());
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
        Ingredient ingredientToBeUpdated = findById(id);
        ingredientToBeUpdated.setUpdatedAt(null);
        Field[] fields = ingredient.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                Object attribute = field.get(ingredient);
                if(Objects.nonNull(attribute)){
                    field.set(ingredientToBeUpdated, attribute);
                }
            }
            ingredientToBeUpdated.setUpdatedAt(LocalDateTime.now());
            return save(ingredientToBeUpdated);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void checkNullabilityOfParameters(Object... objects) {
        for (Object o : objects) {
            if(Objects.isNull(o)) throw new NullParameterException("Parameter must not be null!");
        }
    }
}
