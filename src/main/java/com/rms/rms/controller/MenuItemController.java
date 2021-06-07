package com.rms.rms.controller;

import com.rms.rms.dto.menu.item.MenuItemCreateDto;
import com.rms.rms.dto.menu.item.MenuItemResponseDto;
import com.rms.rms.dto.menu.item.MenuItemUpdateAmountDto;
import com.rms.rms.dto.menu.item.MenuItemUpdateDto;
import com.rms.rms.entity.MenuItemIngredient;
import com.rms.rms.service.MenuItemService;
import com.rms.rms.utils.Path;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Path.MENU_ITEM_PATH)
@AllArgsConstructor
public class MenuItemController {

    private final MenuItemService menuItemService;

    @PostMapping
    public ResponseEntity<MenuItemResponseDto> save(@RequestBody MenuItemCreateDto menuItem) {
        return new ResponseEntity<>(menuItemService.save(menuItem), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MenuItemResponseDto>> getAll() {
        return new ResponseEntity<>(menuItemService.findAll(), HttpStatus.OK);
    }

    @GetMapping(Path.ID)
    public ResponseEntity<MenuItemResponseDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(menuItemService.findById(id), HttpStatus.OK);
    }

    @PutMapping(Path.ID)
    public ResponseEntity<MenuItemResponseDto> update(@PathVariable Long id, @RequestBody MenuItemUpdateDto menuItem) {
        return new ResponseEntity<>(menuItemService.update(id, menuItem), HttpStatus.CREATED);
    }

    @PostMapping(Path.ID)
    public ResponseEntity<MenuItemResponseDto> addIngredientToMenuItem(@PathVariable Long id, @RequestBody MenuItemIngredient menuItemIngredient) {
        return new ResponseEntity<>(menuItemService.addIngredientToMenuItem(id, menuItemIngredient), HttpStatus.CREATED);
    }

    @DeleteMapping(Path.MENU_ID_INGREDIENT)
    public void deleteIngredientFromMenuItem(@PathVariable Long menuItemId, @PathVariable Long ingredientId) {
        menuItemService.deleteIngredientFromMenuItem(menuItemId, ingredientId);
    }

    @PutMapping(Path.MENU_ID_INGREDIENT)
    public ResponseEntity<MenuItemResponseDto> updateAmountOfIngredientAtMenuItem(@PathVariable Long ingredientId, @PathVariable Long menuItemId, @RequestBody MenuItemUpdateAmountDto amount) {
        return new ResponseEntity<>(menuItemService.updateIngredientAmountOfMenuItem(menuItemId, ingredientId, amount), HttpStatus.CREATED);
    }


}
