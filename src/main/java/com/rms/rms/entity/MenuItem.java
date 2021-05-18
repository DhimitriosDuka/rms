package com.rms.rms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rms.rms.enums.Category;
import com.rms.rms.enums.Course;
import com.rms.rms.enums.Type;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "menu_item")
@Data
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private Integer michelinStars;
    private Course course;
    private String description;
    private Type type;
    private Category category;
    private Boolean available;

    @OneToMany(mappedBy = "menuItem")
    private List<MenuItemIngredient> menuItemIngredientList;


}
