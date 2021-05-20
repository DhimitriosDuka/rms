package com.rms.rms.repository;

import com.rms.rms.entity.MenuItemIngredient;
import com.rms.rms.entity.embedded.MenuItemIngredientId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface MenuItemIngredientRepository extends JpaRepository<MenuItemIngredient, MenuItemIngredientId> {
    Optional<MenuItemIngredient> findByMenuItemIngredientIdIngredientIdAndMenuItemIngredientIdMenuItemId(Long menuItemIngredientIdIngredientId, Long menuItemIngredientIdMenuItemId);

    @Transactional
    void deleteByMenuItemIngredientId(MenuItemIngredientId menuItemIngredientId);

}
