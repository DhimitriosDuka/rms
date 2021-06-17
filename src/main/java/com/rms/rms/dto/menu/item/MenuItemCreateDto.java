package com.rms.rms.dto.menu.item;

import com.rms.rms.enums.Category;
import com.rms.rms.enums.Course;
import com.rms.rms.enums.Currency;
import com.rms.rms.enums.Type;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Setter
public class MenuItemCreateDto {

    @NotBlank(message = "Name must not be null!")
    private String name;

    @NotNull(message = "Price must not be null!")
    @Min(value = 0, message = "Price should be greater than or 0!")
    private Double price;

    private Integer michelinStars;

    private Course course;

    @NotBlank(message = "Description must not be blank!")
    private String description;

    @NotNull(message = "Type must not be null!")
    private Type type;

    @NotNull(message = "Currency must not be null!")
    private Currency currency;

    @NotNull(message = "Category must not be null!")
    private Category category;

}
