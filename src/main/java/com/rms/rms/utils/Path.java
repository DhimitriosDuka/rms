package com.rms.rms.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Path {

    public static final String ID = "/{id}";
    public static final String SAVE = "/save";
    public static final String ALL = "/all";
    public static final String REPORT = "/report";
    public static final String LOGIN = "/login";
    public static final String FOOD_GROUP_REPORT = REPORT + "/food-group";
    public static final String ALL_ORDERS_REPORT = REPORT + "/all";
    public static final String INGREDIENT_PATH = "/ingredients";
    public static final String TOP_N_PATH = "/top/{n}";
    public static final String USER_PATH = "/users";
    public static final String MENU_ITEM_PATH = "/menuItems";
    public static final String ORDER_PATH = "/orders";
    public static final String UPDATE_ROLE_OF_USER_WITH_ID = ID + "/role";
    public static final String UPDATE_PASSWORD_OF_USER_WITH_ID = ID + "/password";
    public static final String SCHEDULE_PATH = "/{userId}/schedule";
    public static final String SCHEDULE_UPDATE_PATH = "/schedule/{scheduleId}";
    public static final String MENU_ID_INGREDIENT = "/{menuItemId}/ingredients/{ingredientId}";
    public static final String DELETE_MENU_ITEM_FROM_ORDER = "/{orderId}/menu-item/{menuItemId}";
    public static final String ADD_MENU_ITEM_TO_ORDER = "/{id}/menu-item";
    public static final String SET_MENU_ITEM_UNAVAILABLE = "/{menuItemId}/unavailable";
    public static final String SET_MENU_ITEM_AVAILABLE = "/{menuItemId}/available";
    public static final String UPDATE_STATUS_ORDER_PATH = "/{id}/status";
    public static final String CANCEL_ORDER_PATH = ID + "/status/cancel";


}
