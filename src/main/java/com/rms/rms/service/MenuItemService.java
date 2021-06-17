package com.rms.rms.service;

import com.rms.rms.dto.menu.item.*;
import com.rms.rms.entity.MenuItem;
import com.rms.rms.entity.MenuItemIngredient;
import com.rms.rms.filters.MenuItemFilter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface MenuItemService extends BaseService<MenuItemCreateDto, MenuItemUpdateDto, MenuItemResponseDto, MenuItem>{

    MenuItemResponseDto update(Long id, MenuItemUpdateDto menuItem);
    MenuItemResponseDto addIngredientToMenuItem( Long menuItemId, MenuItemIngredient menuItemIngredient);
    void deleteIngredientFromMenuItem(Long menuItemId, Long ingredientId);
    MenuItemIngredientResponseDto updateIngredientAmountOfMenuItem(Long menuItemId, Long ingredientId, MenuItemUpdateAmountDto amount);
    List<MenuItemResponseDto> findTopIngredients(Integer n);
    List<MenuItemResponseDto> findAllByFilter(MenuItemFilter menuItemFilter);

}
