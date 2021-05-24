package com.rms.rms.service.implementation;

import com.rms.rms.dto.ingredient.IngredientCreateDto;
import com.rms.rms.dto.ingredient.IngredientResponseDto;
import com.rms.rms.dto.ingredient.IngredientUpdateDto;
import com.rms.rms.entity.Ingredient;
import com.rms.rms.exception.IngredientException;
import com.rms.rms.mapper.IngredientMapper;
import com.rms.rms.repository.IngredientsRepository;
import com.rms.rms.service.IngredientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Validated
public class IngredientServiceImpl implements IngredientService {

    private final IngredientsRepository ingredientsRepository;
    public final IngredientMapper ingredientMapper;

    @Override
    public IngredientResponseDto save(@Valid IngredientCreateDto ingredient) {
        ingredientsRepository.findByName(ingredient.getName())
                .ifPresent(value -> {
                    throw new IngredientException("Ingredient with name: " + value.getName() + " already exists!");
                });
        return ingredientMapper.entityToResponseDto(ingredientsRepository.save(ingredientMapper.createDtoToEntity(ingredient)));
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
        return ingredientsRepository.findById(id)
                    .map(ingredientMapper::entityToResponseDto)
                    .orElseThrow(() -> new IngredientException("Ingredient with id: " + id + " does not exists!"));
    }

    @Override
    public IngredientResponseDto update(@NotNull Long id, @Valid IngredientUpdateDto ingredient) {
        ingredientsRepository.findById(id)
                .orElseThrow(() -> new IngredientException("Ingredient with id: " + id + " does not exists!"));

        Ingredient entityIngredient = ingredientMapper.updateDtoToEntity(ingredient);

        ingredientsRepository.findByName(entityIngredient.getName())
                .ifPresent(value -> {
                    if(!value.getId().equals(id)) {
                        throw new IngredientException("Ingredient with name: " + value.getName() + " already exists!");
                    }
                });
        entityIngredient.setId(id);
        entityIngredient.setUpdatedAt(LocalDateTime.now());
        return ingredientMapper.entityToResponseDto(ingredientsRepository.save(entityIngredient));

    }

}
