package com.rms.rms.repository.implemetations;

import com.rms.rms.entity.Ingredient;
import com.rms.rms.filters.IngredientFilter;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class IngredientRepositoryImpl extends BaseImpl<Ingredient, IngredientFilter> {

    @Override
    public List<Ingredient> findAllByFilter(IngredientFilter ingredientFilter) {

        CriteriaQuery<Ingredient> criteriaQuery = criteriaBuilder.createQuery(Ingredient.class);
        Root<Ingredient> rootQuery = criteriaQuery.from(Ingredient.class);
        List<Predicate> predicates = new ArrayList<>();

        if(Objects.nonNull(ingredientFilter)) {
            if(ingredientFilter.getName() != null) {
                predicates.add(criteriaBuilder.like(rootQuery.get("name"), "%" + ingredientFilter.getName() + "%"));
            }

            if (ingredientFilter.getCalories() != null) {
                predicates.add(criteriaBuilder.lessThan(rootQuery.get("calories"), ingredientFilter.getCalories()));
            }

            if (ingredientFilter.getCholesterol() != null) {
                predicates.add(criteriaBuilder.lessThan(rootQuery.get("cholesterol"), ingredientFilter.getCholesterol()));
            }

            if (ingredientFilter.getFat() != null) {
                predicates.add(criteriaBuilder.lessThan(rootQuery.get("fat"), ingredientFilter.getFat()));
            }

            if (ingredientFilter.getFoodGroup() != null) {
                predicates.add(criteriaBuilder.equal(rootQuery.get("foodGroup"), ingredientFilter.getFoodGroup()));
            }
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(criteriaQuery).getResultList();

    }
}
