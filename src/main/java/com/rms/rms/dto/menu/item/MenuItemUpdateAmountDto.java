package com.rms.rms.dto.menu.item;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MenuItemUpdateAmountDto {

    @NotNull(message = "Amount must not be null!")
    private Integer amount;

}
