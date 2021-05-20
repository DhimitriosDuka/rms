package com.rms.rms.controller;

import com.rms.rms.dto.ingredient.IngredientCreateDto;
import com.rms.rms.dto.ingredient.IngredientResponseDto;
import com.rms.rms.dto.ingredient.IngredientUpdateDto;
import com.rms.rms.mapper.IngredientMapper;
import com.rms.rms.service.IngredientService;
import com.rms.rms.utils.Path;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Path.INGREDIENT_PATH)
@AllArgsConstructor
public class IngredientController {

    public final IngredientService ingredientService;
    public final IngredientMapper ingredientMapper;

    @PostMapping
    public ResponseEntity<IngredientResponseDto> save(@RequestBody IngredientCreateDto ingredient) {
        return new ResponseEntity<>(ingredientService.save(ingredient), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<IngredientResponseDto>> getAll() {
        return new ResponseEntity<>(ingredientService.findAll(), HttpStatus.OK);
    }

    @GetMapping(Path.ID)
    public ResponseEntity<IngredientResponseDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(ingredientService.findById(id), HttpStatus.OK);
    }

    @PutMapping(Path.ID)
    public ResponseEntity<IngredientResponseDto> update(@PathVariable Long id, @RequestBody IngredientUpdateDto ingredient) {
        return new ResponseEntity<>(ingredientService.update(id, ingredient), HttpStatus.CREATED);
    }

}

