package com.rms.rms.dto.order;

import com.rms.rms.dto.menu.item.MenuItemResponseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderMenuItemResponseDto {

    private Double amount;
    private String note;
    private MenuItemResponseDto menuItem;

}
