package com.rms.rms.dto.menu.item;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class MenuItemUpdateAmountDto {

    @NotNull(message = "Amount must not be null!")
    @Min(value = 0, message = "Amount must be grater than 0!")
    private Integer amount;

}
