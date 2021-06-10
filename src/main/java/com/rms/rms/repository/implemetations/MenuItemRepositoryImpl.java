package com.rms.rms.repository.implemetations;

import com.rms.rms.entity.MenuItem;
import com.rms.rms.filters.MenuItemFilter;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Component
public class MenuItemRepositoryImpl extends BaseImpl<MenuItem, MenuItemFilter> {

    @Override
    public List<MenuItem> findAllByFilter(MenuItemFilter menuItemFilter) {

        CriteriaQuery<MenuItem> criteriaQuery = criteriaBuilder.createQuery(MenuItem.class);
        Root<MenuItem> rootQuery = criteriaQuery.from(MenuItem.class);
        List<Predicate> predicates = new ArrayList<>();

        if (menuItemFilter != null) {

            if(menuItemFilter.getName() != null) {
                predicates.add(criteriaBuilder.like(rootQuery.get("name"), "%" + menuItemFilter.getName() + "%"));
            }

            if(menuItemFilter.getCategory() != null) {
                predicates.add(criteriaBuilder.equal(rootQuery.get("category"), menuItemFilter.getCategory()));
            }

            if(menuItemFilter.getCourse() != null) {
                predicates.add(criteriaBuilder.equal(rootQuery.get("course"), menuItemFilter.getCourse()));
            }

            if(menuItemFilter.getMichelinStarts() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(rootQuery.get("michelinStars"), menuItemFilter.getMichelinStarts()));
            }

            if(menuItemFilter.getType() != null) {
                predicates.add(criteriaBuilder.equal(rootQuery.get("type"), menuItemFilter.getType()));
            }

            criteriaQuery.where(predicates.toArray(new Predicate[0]));

        }

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
