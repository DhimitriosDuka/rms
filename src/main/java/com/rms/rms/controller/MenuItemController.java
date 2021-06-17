package com.rms.rms.controller;

import com.rms.rms.dto.menu.item.*;
import com.rms.rms.entity.MenuItemIngredient;
import com.rms.rms.enums.Category;
import com.rms.rms.filters.MenuItemFilter;
import com.rms.rms.service.MenuItemService;
import com.rms.rms.utils.Path;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping(Path.MENU_ITEM_PATH)
@AllArgsConstructor
public class MenuItemController {

    private final MenuItemService menuItemService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<MenuItemResponseDto> save(@Valid @RequestBody MenuItemCreateDto menuItem) {
        return new ResponseEntity<>(menuItemService.save(menuItem), HttpStatus.CREATED);
    }

    @PostMapping(Path.ALL)
    public ResponseEntity<List<MenuItemResponseDto>> getAll(@Valid @RequestBody(required = false) MenuItemFilter menuItemFilter) {
        return new ResponseEntity<>(menuItemService.findAllByFilter(menuItemFilter), HttpStatus.OK);
    }

    @GetMapping(Path.ID)
    public ResponseEntity<MenuItemResponseDto> getById(@PathVariable @Min(0) Long id) {
        return new ResponseEntity<>(menuItemService.findById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(Path.ID)
    public ResponseEntity<MenuItemResponseDto> update(@PathVariable @Min(0) Long id, @Valid @RequestBody MenuItemUpdateDto menuItem) {
        return new ResponseEntity<>(menuItemService.update(id, menuItem), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(Path.ID)
    public ResponseEntity<MenuItemResponseDto> addIngredientToMenuItem(@PathVariable @Min(0) Long id, @Valid @RequestBody MenuItemIngredient menuItemIngredient) {
        return new ResponseEntity<>(menuItemService.addIngredientToMenuItem(id, menuItemIngredient), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(Path.MENU_ID_INGREDIENT)
    public void deleteIngredientFromMenuItem(@PathVariable  @Min(0) Long menuItemId, @PathVariable @Min(0) Long ingredientId) {
        menuItemService.deleteIngredientFromMenuItem(menuItemId, ingredientId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(Path.MENU_ID_INGREDIENT)
    public ResponseEntity<MenuItemIngredientResponseDto> updateAmountOfIngredientAtMenuItem(@PathVariable @Min(0) Long ingredientId, @PathVariable @Min(0) Long menuItemId, @Valid @RequestBody MenuItemUpdateAmountDto amount) {
        return new ResponseEntity<>(menuItemService.updateIngredientAmountOfMenuItem(menuItemId, ingredientId, amount), HttpStatus.CREATED);
    }

    @GetMapping(Path.TOP_N_PATH)
    public ResponseEntity<List<MenuItemResponseDto>> findTopMenuItems(@PathVariable @Range(min = 0, max = 10) Integer n) {
        return new ResponseEntity<>(menuItemService.findTopIngredients(n), HttpStatus.OK);
    }


}
