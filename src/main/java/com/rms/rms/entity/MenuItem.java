package com.rms.rms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rms.rms.enums.Category;
import com.rms.rms.enums.Course;
import com.rms.rms.enums.Currency;
import com.rms.rms.enums.Type;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "menu_item",  uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
@Data
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @NotNull(message = "Currency must not be null!")
    private Currency currency;

    private Category category;

    private Boolean available = Boolean.TRUE;

    private Double calories = 0.0;

    @OneToMany(mappedBy = "menuItem")
    private List<MenuItemIngredient> menuItemIngredientList;

    @OneToMany(mappedBy = "menuItem")
    @JsonIgnore
    private List<OrderMenuItem> orderMenuItems;


}
