package com.rms.rms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rms.rms.entity.embedded.MenuItemIngredientId;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

    @ManyToOne()
    @MapsId("ingredientId")
    private Ingredient ingredient;

    @NotNull(message = "Amount must not be null!")
    private Integer amount;

}
