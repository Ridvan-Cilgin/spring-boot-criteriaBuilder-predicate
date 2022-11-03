package com.ridvancilgin.springbootcriteriabuilderpredicate.repository.specification;

import com.ridvancilgin.springbootcriteriabuilderpredicate.common.AbstractQuerySpecification;
import com.ridvancilgin.springbootcriteriabuilderpredicate.entity.Book;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class BookSpec extends AbstractQuerySpecification<Book> {

    private Long id;
    private String name;
    private String author;
    private BigDecimal price;
    private String startDate;

    @Override
    public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        addFieldEqualsPredicate(criteriaBuilder, root, predicates, "id");
        addFieldEqualsPredicate(criteriaBuilder, root, predicates, "name");
        addFieldEqualsPredicate(criteriaBuilder, root, predicates, "author");
        addFieldEqualsPredicate(criteriaBuilder, root, predicates, "price");
        addDateGreaterThanOrEqualToPredicate(criteriaBuilder, root, predicates, "startDate", "startDate");

        query.orderBy(criteriaBuilder.desc(root.get("startDate")));

        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
