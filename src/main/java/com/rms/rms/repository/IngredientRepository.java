package com.rms.rms.repository;

import com.rms.rms.entity.Ingredient;
import com.rms.rms.filters.IngredientFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    boolean existsById(Long id);

    List<Ingredient> findAllByFilter(IngredientFilter ingredientFilter);

}
