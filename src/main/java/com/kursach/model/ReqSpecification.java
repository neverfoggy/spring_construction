package com.kursach.model;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ReqSpecification implements Specification<DAORequest> {

    private DAORequest filter;

    public ReqSpecification(DAORequest filter) {
        super();
        this.filter = filter;
    }

    public Predicate toPredicate(Root<DAORequest> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

        Predicate p = cb.disjunction();

        if (filter.getBuild() != null && filter.getId() != -1) {

            p.getExpressions().add(
                    cb.or(cb.equal(root.get("build"), filter.getBuild()),
                            cb.equal(root.get("id"), filter.getId())));

        }
        return p;

    }

}
