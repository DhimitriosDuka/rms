package com.rms.rms.utils;


import com.rms.rms.dto.menu.item.MenuItemResponseDto;
import com.rms.rms.dto.menu.item.MenuItemUpdateDto;
import com.rms.rms.entity.Ingredient;
import com.rms.rms.entity.MenuItem;
import com.rms.rms.entity.MenuItemIngredient;
import com.rms.rms.entity.embedded.MenuItemIngredientId;
import com.rms.rms.enums.Category;
import com.rms.rms.enums.Course;
import com.rms.rms.enums.Currency;
import com.rms.rms.enums.Type;

import java.util.Arrays;
import java.util.Random;

public class MenuItemUtil {

    private final IngredientUtil ingredientUtil;

    public MenuItemUtil() {
        ingredientUtil = new IngredientUtil();
    }

    public MenuItem initEntity(Long id) {

        Random random = new Random();

        MenuItem menuItem = new MenuItem();
        menuItem.setId(id);
        menuItem.setName("MenuItem" + id);
        menuItem.setAvailable(Boolean.TRUE);
        menuItem.setCalories(random.nextDouble());
        menuItem.setCategory(Category.getRandomCategory());
        menuItem.setCourse(Course.getRandomCourse());
        menuItem.setCurrency(Currency.getRandomCurrency());
        menuItem.setDescription("Description");
        menuItem.setMichelinStars(3);
        menuItem.setType(Type.getRandomType());
        menuItem.setPrice(random.nextDouble());

        menuItem.setMenuItemIngredientList(Arrays.asList(
                initMenuItemIngredient(menuItem, ingredientUtil.initEntity(1L)),
                initMenuItemIngredient(menuItem, ingredientUtil.initEntity(2L))
        ));

        return menuItem;
    }

    public MenuItemIngredient initMenuItemIngredient(MenuItem menuItem, Ingredient ingredient) {
        MenuItemIngredient menuItemIngredient = new MenuItemIngredient();
        menuItemIngredient.setMenuItemIngredientId(new MenuItemIngredientId(ingredient.getId(), menuItem.getId()));
        menuItemIngredient.setIngredient(ingredient);
        menuItemIngredient.setMenuItem(menuItem);
        menuItemIngredient.setAmount(new Random(100).nextInt());
        return menuItemIngredient;
    }

    public MenuItemResponseDto convertToMenuItemResponseDto(MenuItem entity) {

        MenuItemResponseDto menuItemResponseDto = new MenuItemResponseDto();
        menuItemResponseDto.setName(entity.getName());
        menuItemResponseDto.setAvailable(entity.getAvailable());
        menuItemResponseDto.setCalories(entity.getCalories());
        menuItemResponseDto.setCategory(entity.getCategory());
        menuItemResponseDto.setCourse(entity.getCourse());
        menuItemResponseDto.setCurrency(entity.getCurrency());
        menuItemResponseDto.setDescription(entity.getDescription());
        menuItemResponseDto.setMichelinStars(entity.getMichelinStars());
        menuItemResponseDto.setType(entity.getType());
        return menuItemResponseDto;

    }

    public MenuItemUpdateDto convertToMenuItemUpdateDto(MenuItem entity) {
        MenuItemUpdateDto menuItemUpdateDto = new MenuItemUpdateDto();
        menuItemUpdateDto.setName(entity.getName());
        menuItemUpdateDto.setCategory(entity.getCategory());
        menuItemUpdateDto.setCourse(entity.getCourse());
        menuItemUpdateDto.setCurrency(entity.getCurrency());
        menuItemUpdateDto.setDescription(entity.getDescription());
        menuItemUpdateDto.setMichelinStars(entity.getMichelinStars());
        menuItemUpdateDto.setType(entity.getType());
        return menuItemUpdateDto;
    }

    public MenuItemIngredient initMenuItemIngredient(MenuItem menuItem){
        MenuItemIngredient menuItemIngredient = new MenuItemIngredient();
        menuItemIngredient.setMenuItem(menuItem);
        menuItemIngredient.setIngredient(ingredientUtil.initEntity(1L));
        menuItemIngredient.setAmount(new Random(100).nextInt());
        menuItemIngredient.setMenuItemIngredientId(new MenuItemIngredientId(menuItemIngredient.getIngredient().getId(), menuItem.getId()));
        return menuItemIngredient;
    }

}
