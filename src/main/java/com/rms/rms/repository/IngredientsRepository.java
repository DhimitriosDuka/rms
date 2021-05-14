package com.rms.rms.repository;

import com.rms.rms.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IngredientsRepository extends JpaRepository<Ingredient, Long> {
    Optional<Ingredient> findByName(String name);
    boolean existsById(Long id);
}
