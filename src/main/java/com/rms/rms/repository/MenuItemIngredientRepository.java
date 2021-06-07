package com.rms.rms.repository;

import com.rms.rms.entity.MenuItemIngredient;
import com.rms.rms.entity.embedded.MenuItemIngredientId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface MenuItemIngredientRepository extends JpaRepository<MenuItemIngredient, MenuItemIngredientId> {

    Optional<MenuItemIngredient> findByMenuItemIngredientId(MenuItemIngredientId menuItemIngredientId);

    @Transactional
    void deleteByMenuItemIngredientId(MenuItemIngredientId menuItemIngredientId);

    @Query(value = "SELECT menu_item_ingredient.ingredient_id\n" +
            "FROM menu_item_ingredient\n" +
            "LEFT JOIN menu_item ON menu_item_ingredient.ingredient_id = menu_item.id\n" +
            "LEFT JOIN ingredient ON ingredient.id = menu_item_ingredient.ingredient_id\n" +
            "GROUP BY menu_item_ingredient.ingredient_id\n" +
            "ORDER BY COUNT(menu_item_ingredient.ingredient_id)\n" +
            "LIMIT ?", nativeQuery = true)
    List<Long> findTopIngredients(Integer n);

}
