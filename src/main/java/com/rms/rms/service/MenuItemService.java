package com.rms.rms.service;

import com.rms.rms.dto.menu.item.MenuItemCreateDto;
import com.rms.rms.dto.menu.item.MenuItemResponseDto;
import com.rms.rms.dto.menu.item.MenuItemUpdateDto;
import com.rms.rms.entity.Ingredient;
import com.rms.rms.entity.MenuItemIngredient;

import java.util.List;

public interface MenuItemService {

    MenuItemResponseDto save(MenuItemCreateDto menuItem);
    List<MenuItemResponseDto> findAllAvailable();
    MenuItemResponseDto findById(Long id);
    MenuItemResponseDto update(Long id, MenuItemUpdateDto menuItem);
    MenuItemResponseDto addIngredientToMenuItem(Long menuItemId, MenuItemIngredient menuItemIngredient);
    void deleteIngredientFromMenuItem(Long menuItemId, Long ingredientId);
    MenuItemResponseDto updateIngredientAmountOfMenuItem(Long menuItemId, Long ingredientId, Integer amount);

}
