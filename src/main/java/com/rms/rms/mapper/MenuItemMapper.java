package com.rms.rms.mapper;

import com.rms.rms.dto.menu.item.MenuItemCreateDto;
import com.rms.rms.dto.menu.item.MenuItemResponseDto;
import com.rms.rms.dto.menu.item.MenuItemUpdateDto;
import com.rms.rms.entity.MenuItem;
import org.springframework.stereotype.Component;

@Component
public class MenuItemMapper extends BaseMapper<MenuItemCreateDto, MenuItemUpdateDto, MenuItemResponseDto, MenuItem>{

    public MenuItemMapper() {
        super(MenuItemResponseDto.class, MenuItem.class);
    }

}

