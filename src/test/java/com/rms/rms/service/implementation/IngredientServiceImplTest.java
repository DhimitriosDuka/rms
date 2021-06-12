package com.rms.rms.service.implementation;

import com.rms.rms.dto.ingredient.IngredientCreateDto;
import com.rms.rms.dto.ingredient.IngredientResponseDto;
import com.rms.rms.dto.ingredient.IngredientUpdateDto;
import com.rms.rms.entity.Ingredient;
import com.rms.rms.utils.IngredientUtil;
import org.hibernate.JDBCException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IngredientServiceImplTest extends BaseServiceTest<IngredientCreateDto, IngredientUpdateDto, IngredientResponseDto, Ingredient>{

    @InjectMocks
    private IngredientServiceImpl ingredientService;

    private IngredientUtil ingredientUtil;
    private Ingredient ingredient;
    private IngredientCreateDto ingredientCreateDto;
    private IngredientUpdateDto ingredientUpdateDto;
    private IngredientResponseDto ingredientResponseDto;

    @Override
    @BeforeEach
    void init() {
        ingredientUtil = new IngredientUtil();
        ingredient = ingredientUtil.initEntity(1L);
        ingredientCreateDto = ingredientUtil.convertEntityToCreateDto(ingredient);
        ingredientUpdateDto = ingredientUtil.convertEntityToUpdateDto(ingredient);
        ingredientResponseDto = ingredientUtil.covertToResponseDto(ingredient);
    }

    @Test
    void When_FindAll_ReturnIngredientList() {

        List<Ingredient> ingredients = ingredientUtil.initEntityList(new Random(10).nextInt());

        when(jpaRepository.findAll()).thenReturn(ingredients);
        assertEquals(ingredientService.findAll().size(), ingredients.size());

    }

    @Test
    void When_FindById_ReturnIngredient() {

        when(jpaRepository.findById(ingredient.getId())).thenReturn(Optional.of(ingredient));
        when(baseMapper.entityToResponseDto(ingredient)).thenReturn(ingredientResponseDto);
        assertEquals(ingredientService.findById(ingredient.getId()).getName(), ingredient.getName());

    }

    @Test
    void When_FindById_ThrowRuntimeException() {

        when(jpaRepository.findById(ingredient.getId())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> ingredientService.findById(ingredient.getId()));

    }

    @Test
    void When_Save_ReturnSavedIngredient() {

        when(jpaRepository.save(ingredient)).thenReturn(ingredient);
        when(baseMapper.createDtoToEntity(ingredientCreateDto)).thenReturn(ingredient);
        when(baseMapper.entityToResponseDto(ingredient)).thenReturn(ingredientResponseDto);
        assertEquals(ingredientService.save(ingredientCreateDto).getName(), ingredientResponseDto.getName());

    }

    @Test
    void When_Save_ThrowJDBCException() {

        when(jpaRepository.save(ingredient)).thenThrow(JDBCException.class);
        when(baseMapper.createDtoToEntity(ingredientCreateDto)).thenReturn(ingredient);
        assertThrows(JDBCException.class, () -> ingredientService.save(ingredientCreateDto));

    }

    @Test
    void When_Update_ReturnUpdatedIngredient() {

        when(jpaRepository.findById(ingredient.getId())).thenReturn(Optional.of(ingredient));
        when(baseMapper.updateDtoToEntity(ingredientUpdateDto)).thenReturn(ingredient);
        when(baseMapper.entityToResponseDto(ingredient)).thenReturn(ingredientResponseDto);
        when(jpaRepository.save(ingredient)).thenReturn(ingredient);

        assertEquals(ingredientService.update(ingredient.getId(), ingredientUpdateDto).getId(), ingredient.getId());


    }

    @Test
    void When_Update_ThrowRuntimeExceptionIngredientWithIdDoesNotExit() {

        when(jpaRepository.findById(ingredient.getId())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> ingredientService.update(ingredient.getId(), ingredientUpdateDto));

    }

    @Test
    void When_Update_ThrowJDBCExceptionIngredientWithNameExists() {

        when(jpaRepository.findById(ingredient.getId())).thenReturn(Optional.of(ingredient));
        when(baseMapper.updateDtoToEntity(ingredientUpdateDto)).thenReturn(ingredient);
        when(baseMapper.entityToResponseDto(ingredient)).thenReturn(ingredientResponseDto);
        when(jpaRepository.save(ingredient)).thenThrow(JDBCException.class);

        assertThrows(JDBCException.class, () -> ingredientService.update(ingredient.getId(), ingredientUpdateDto));

    }

}