package com.rms.rms.dto.menu.item;

import com.rms.rms.enums.Category;
import com.rms.rms.enums.Course;
import com.rms.rms.enums.Type;
import lombok.Data;


@Data
public class MenuItemCreateDto {

    private String name;
    private Double price;
    private Integer michelinStars;
    private Course course;
    private String description;
    private Type type;
    private Category category;

}
