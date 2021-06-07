package com.rms.rms.repository.implemetations;

import com.rms.rms.entity.Order;
import com.rms.rms.filters.OrderFilter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@AllArgsConstructor
public class OrderRepositoryImpl extends BaseImpl<Order, OrderFilter>{

    @Override
    public List<Order> findAllByFilter(OrderFilter orderFilter) {

        CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
        Root<Order> rootQuery = criteriaQuery.from(Order.class);
        List<Predicate> predicates = new ArrayList<>();

        if(Objects.nonNull(orderFilter)){
            if(Objects.nonNull(orderFilter.getStartDate())) {
                if(Objects.nonNull(orderFilter.getEndDate())) {
                    predicates.add(criteriaBuilder.between(rootQuery.get("createdAt"), orderFilter.getStartDate(), orderFilter.getEndDate()));
                }else {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(rootQuery.get("createdAt"), orderFilter.getStartDate()));
                }
            } else if(Objects.nonNull(orderFilter.getEndDate())){
                predicates.add(criteriaBuilder.lessThanOrEqualTo(rootQuery.get("createdAt"), orderFilter.getStartDate()));
            }

            if(Objects.nonNull(orderFilter.getStatus())) {
                predicates.add(criteriaBuilder.equal(rootQuery.get("status"), orderFilter.getStatus()));
            }

            if(Objects.nonNull(orderFilter.getTotalPrice())) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(rootQuery.get("totalPrice"), orderFilter.getTotalPrice()));
            }

            criteriaQuery.where(predicates.toArray(new Predicate[0]));
        }

        return entityManager.createQuery(criteriaQuery).getResultList();

    }


}
