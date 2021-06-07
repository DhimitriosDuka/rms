package com.rms.rms.service;

import com.rms.rms.dto.menu.item.MenuItemCreateDto;
import com.rms.rms.dto.menu.item.MenuItemResponseDto;
import com.rms.rms.dto.menu.item.MenuItemUpdateAmountDto;
import com.rms.rms.dto.menu.item.MenuItemUpdateDto;
import com.rms.rms.entity.MenuItem;
import com.rms.rms.entity.MenuItemIngredient;
import com.rms.rms.filters.MenuItemFilter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface MenuItemService extends BaseService<MenuItemCreateDto, MenuItemUpdateDto, MenuItemResponseDto, MenuItem>{

    MenuItemResponseDto update(@NotNull Long id, @Valid MenuItemUpdateDto menuItem);
    MenuItemResponseDto addIngredientToMenuItem(@NotNull Long menuItemId, @Valid MenuItemIngredient menuItemIngredient);
    void deleteIngredientFromMenuItem(@NotNull Long menuItemId, @NotNull Long ingredientId);
    MenuItemResponseDto updateIngredientAmountOfMenuItem(@NotNull Long menuItemId, @NotNull Long ingredientId, @NotNull MenuItemUpdateAmountDto amount);
    List<MenuItemResponseDto> findTopIngredients(Integer n);
    List<MenuItemResponseDto> findAllByFilter(MenuItemFilter menuItemFilter);

}
