package com.rms.rms.service.implementation;

import com.rms.rms.dto.ingredient.IngredientCreateDto;
import com.rms.rms.dto.ingredient.IngredientResponseDto;
import com.rms.rms.dto.ingredient.IngredientUpdateDto;
import com.rms.rms.entity.Ingredient;
import com.rms.rms.filters.IngredientFilter;
import com.rms.rms.repository.IngredientRepository;
import com.rms.rms.repository.MenuItemIngredientRepository;
import com.rms.rms.service.IngredientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Validated
public class IngredientServiceImpl extends BaseServiceImpl<IngredientCreateDto, IngredientUpdateDto, IngredientResponseDto, Ingredient> implements IngredientService{

    private final MenuItemIngredientRepository menuItemIngredientRepository;
    private final IngredientRepository ingredientsRepository;

    @Override
    public IngredientResponseDto update(Long id, IngredientUpdateDto ingredient) {
        super.findById(id);
        Ingredient entityIngredient = baseMapper.updateDtoToEntity(ingredient);
        entityIngredient.setId(id);
        entityIngredient.setUpdatedAt(LocalDateTime.now());
        return baseMapper.entityToResponseDto(jpaRepository.save(entityIngredient));

    }

    @Override
    public List<IngredientResponseDto> findTopIngredients(Integer n) {
        return menuItemIngredientRepository.findTopIngredients(n)
                .stream()
                .map(id -> baseMapper.entityToResponseDto(jpaRepository.getOne(id)))
                .collect(Collectors.toList());
    }

    @Override
    public List<IngredientResponseDto> findAllByFilter(IngredientFilter ingredientFilter) {
        return ingredientsRepository.findAllByFilter(ingredientFilter)
                .stream()
                .map(ingredient -> baseMapper.entityToResponseDto(ingredient))
                .collect(Collectors.toList());
    }


}
