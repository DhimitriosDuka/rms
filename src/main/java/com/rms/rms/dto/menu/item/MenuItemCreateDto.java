package com.rms.rms.dto.menu.item;

import com.rms.rms.enums.Category;
import com.rms.rms.enums.Course;
import com.rms.rms.enums.Type;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
public class MenuItemCreateDto {

    @NotBlank(message = "Name must not be blank!")
    private String name;

    @NotNull
    @Min(value = 0, message = "Price should be greater than or 0!")
    private Double price;

    private Integer michelinStars;

    private Course course;

    @NotBlank(message = "Description must not be blank!")
    private String description;

    @NotNull(message = "Type must not be null!")
    private Type type;
    private Category category;

}
