package com.rms.rms.service.implementation;

import com.rms.rms.dto.ingredient.IngredientCreateDto;
import com.rms.rms.dto.ingredient.IngredientResponseDto;
import com.rms.rms.dto.ingredient.IngredientUpdateDto;
import com.rms.rms.entity.Ingredient;
import com.rms.rms.exception.IngredientException;
import com.rms.rms.exception.NullParameterException;
import com.rms.rms.mapper.IngredientMapper;
import com.rms.rms.repository.IngredientsRepository;
import com.rms.rms.service.IngredientService;
import com.rms.rms.service.SuperService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class IngredientServiceImpl extends SuperService implements IngredientService {

    private final IngredientsRepository ingredientsRepository;
    public final IngredientMapper ingredientMapper;

    @Override
    public IngredientResponseDto save(@Valid IngredientCreateDto ingredient) {
        checkNullabilityOfParameters(ingredient);
        Ingredient entityIngredient = ingredientMapper.createDtoToEntity(ingredient);
        ingredientsRepository.findByName(entityIngredient.getName())
                .ifPresent(value -> {
                    throw new IngredientException("Ingredient with name: " + value.getName() + " already exists!");
                });
        return ingredientMapper.entityToResponseDto(ingredientsRepository.save(entityIngredient));
    }

    @Override
    public List<IngredientResponseDto> findAll() {
        return ingredientsRepository.findAll()
                .stream()
                .map(ingredientMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public IngredientResponseDto findById(@NotNull Long id) {
        checkNullabilityOfParameters(id);
        return Optional.of(ingredientsRepository.findById(id))
                    .get()
                    .map(ingredientMapper::entityToResponseDto)
                    .orElseThrow(() -> new IngredientException("Ingredient with id: " + id + " does not exists!"));
    }

    @Override
    public IngredientResponseDto update(@NotNull Long id, @Valid IngredientUpdateDto ingredient) {

        checkNullabilityOfParameters(id, ingredient);

        if(doesNotExistsById(id)) {
            throw new IngredientException("Ingredient with id: " + id + " does not exists!");
        }

        Ingredient entityIngredient = ingredientMapper.updateDtoToEntity(ingredient);

        Optional<Ingredient> existingIngredientWithName = ingredientsRepository.findByName(entityIngredient.getName());
        if(existingIngredientWithName.isPresent()) {
            Ingredient ingredientOptionalValue = existingIngredientWithName.get();
            if(!ingredientOptionalValue.getId().equals(id)) {
                throw new IngredientException("Ingredient with name: " + ingredientOptionalValue.getName() + " already exists!");
            }
        }
        entityIngredient.setId(id);
        entityIngredient.setUpdatedAt(LocalDateTime.now());
        return ingredientMapper.entityToResponseDto(ingredientsRepository.save(entityIngredient));
    }

    private boolean doesNotExistsById(Long id) {
        return !ingredientsRepository.existsById(id);
    }

}
