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
public class IngredientServiceImpl extends BaseServiceImpl<IngredientCreateDto, IngredientUpdateDto, IngredientResponseDto, Ingredient> implements IngredientService{

    @Override
    public IngredientResponseDto update(Long id, IngredientUpdateDto ingredient) {
        super.findById(id);
        Ingredient entityIngredient = baseMapper.updateDtoToEntity(ingredient);
        entityIngredient.setId(id);
        entityIngredient.setUpdatedAt(LocalDateTime.now());
        return baseMapper.entityToResponseDto(jpaRepository.save(entityIngredient));

    }

}
