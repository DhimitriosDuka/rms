package com.rms.rms.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Path {

    public static final String INGREDIENT_PATH = "/ingredients";
    public static final String ID = "/{id}";

    public static final String MENU_ITEM_PATH = "/menuItems";
    public static final String MENU_ID_INGREDIENT = "/{menuItemId}/ingredients/{ingredientId}";
    public static final String SET_MENU_ITEM_UNAVAILABLE = "/{menuItemId}/unavailable";
    public static final String SET_MENU_ITEM_AVAILABLE = "/{menuItemId}/available";

}
