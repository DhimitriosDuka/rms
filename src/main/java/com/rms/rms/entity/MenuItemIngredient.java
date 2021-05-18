package com.rms.rms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "menu_item_ingredient")
@Data
public class MenuItemIngredient {

    @EmbeddedId
    private MenuItemIngredientId menuItemIngredientId;

    @ManyToOne
    @MapsId("menuItemId")
    @JsonIgnore
    private MenuItem menuItem;

    @ManyToOne
    @MapsId("ingredientId")
    private Ingredient ingredient;

    private Integer amount;

}
