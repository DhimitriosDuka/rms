package com.rms.rms.repository.implemetations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public abstract class BaseImpl<ENTITY, FILTER> {

    @Autowired
    protected EntityManager entityManager;

    @Autowired
    protected CriteriaBuilder criteriaBuilder;

    public abstract List<ENTITY> findAllByFilter(FILTER filter);

    @Bean
    public CriteriaBuilder criteriaBuilder() {
        return entityManager.getCriteriaBuilder();
    }

}
