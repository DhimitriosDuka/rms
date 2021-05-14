package com.rms.rms.controller;

import com.rms.rms.dto.ingredient.IngredientCreateDto;
import com.rms.rms.dto.ingredient.IngredientResponseDto;
import com.rms.rms.dto.ingredient.IngredientUpdateDto;
import com.rms.rms.entity.Ingredient;
import com.rms.rms.mapper.IngredientMapper;
import com.rms.rms.service.IngredientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ingredients")
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

    @GetMapping("/{id}")
    public ResponseEntity<IngredientResponseDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(ingredientService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IngredientResponseDto> update(@PathVariable Long id, @RequestBody IngredientUpdateDto ingredient) {
        return new ResponseEntity<>(ingredientService.update(id, ingredient), HttpStatus.CREATED);
    }

}

