package com.rms.rms.mapper;

import com.rms.rms.config.ModelMapperBean;
import com.rms.rms.dto.menu.item.MenuItemCreateDto;
import com.rms.rms.dto.menu.item.MenuItemResponseDto;
import com.rms.rms.dto.menu.item.MenuItemUpdateDto;
import com.rms.rms.entity.MenuItem;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Setter
@Component
public class MenuItemMapper {

    private final ModelMapperBean modelMapperBean;

    public MenuItem createDtoToEntity(MenuItemCreateDto menuItem) {
        return modelMapperBean.modelMapper().map(menuItem, MenuItem.class);
    }

    public MenuItemResponseDto entityToResponseDto(MenuItem menuItem) {
        return modelMapperBean.modelMapper().map(menuItem, MenuItemResponseDto.class);
    }

    public MenuItem updateDtoToEntity(MenuItemUpdateDto menuItem) {
        return modelMapperBean.modelMapper().map(menuItem, MenuItem.class);
    }

}

