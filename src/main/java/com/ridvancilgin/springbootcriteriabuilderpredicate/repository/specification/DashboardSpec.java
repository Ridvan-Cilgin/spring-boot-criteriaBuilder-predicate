//package com.ridvancilgin.springbootcriteriabuilderpredicate.repository.specification;
//
//import com.ykb.digital.garage.domain.data.entity.travel.TravelEntity;
//import com.ykb.digital.garage.domain.service.common.AbstractQuerySpecification;
//import com.ykb.digital.garage.domain.service.travel.model.dto.TravelStatus;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//@Data
//@EqualsAndHashCode(callSuper = false)
//public class TravelDashboardSpec extends AbstractQuerySpecification<TravelEntity> {
//    private static final String STATUS = "status";
//    private static final String START_DATE = "startDate";
//    private Integer clientNo;
//    private TravelStatus statusForPlanned;
//    private TravelStatus statusForStarted;
//    private LocalDateTime startDateForPlannedStatus;
//    private LocalDateTime startDateForStartedStatus;
//    @Override
//    public Predicate toPredicate(Root<TravelEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//        List<Predicate> predicatesForStatusList = new ArrayList<>();
//        List<Predicate> predicatesForPlanned = new ArrayList<>();
//        addFieldEqualsPredicate(criteriaBuilder, root, predicatesForPlanned, "statusForPlanned", STATUS);
//        addLocalDateTimeGreaterThanOrEqualToPredicate(criteriaBuilder, root, predicatesForPlanned, "startDateForPlannedStatus", START_DATE);
//        predicatesForStatusList.add(criteriaBuilder.and(predicatesForPlanned.toArray(new Predicate[0])));
//        List<Predicate> predicatesForStarted = new ArrayList<>();
//        addFieldEqualsPredicate(criteriaBuilder, root, predicatesForStarted, "statusForStarted", STATUS);
//        addLocalDateTimeGreaterThanOrEqualToPredicate(criteriaBuilder, root, predicatesForStarted, "startDateForStartedStatus", START_DATE);
//        predicatesForStatusList.add(criteriaBuilder.and(predicatesForStarted.toArray(new Predicate[0])));
//        List<Predicate> predicatesForClientNo = new ArrayList<>();
//        addFieldEqualsPredicate(criteriaBuilder, root, predicatesForClientNo, "clientNo");
//        predicatesForClientNo.add(criteriaBuilder.or(predicatesForStatusList.toArray(new Predicate[0])));
//        query.orderBy(criteriaBuilder.desc(root.get(START_DATE)));
//        return criteriaBuilder.and(predicatesForClientNo.toArray(new Predicate[0]));
//    }
//}
