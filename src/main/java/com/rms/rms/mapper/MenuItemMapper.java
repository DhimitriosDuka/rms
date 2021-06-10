package com.rms.rms.mapper;

import com.rms.rms.dto.menu.item.MenuItemCreateDto;
import com.rms.rms.dto.menu.item.MenuItemIngredientResponseDto;
import com.rms.rms.dto.menu.item.MenuItemResponseDto;
import com.rms.rms.dto.menu.item.MenuItemUpdateDto;
import com.rms.rms.entity.MenuItem;
import com.rms.rms.entity.MenuItemIngredient;
import org.springframework.stereotype.Component;

@Component
public class MenuItemMapper extends BaseMapper<MenuItemCreateDto, MenuItemUpdateDto, MenuItemResponseDto, MenuItem>{

    public MenuItemMapper() {
        super(MenuItemResponseDto.class, MenuItem.class);
    }

    public MenuItemIngredientResponseDto convertMenuItemIngredientToResponseDto(MenuItemIngredient menuItemIngredient){
        MenuItemIngredientResponseDto dto = new MenuItemIngredientResponseDto();
        dto.setIngredient(menuItemIngredient.getIngredient());
        dto.setAmount(menuItemIngredient.getAmount());
        return dto;
    }

}

