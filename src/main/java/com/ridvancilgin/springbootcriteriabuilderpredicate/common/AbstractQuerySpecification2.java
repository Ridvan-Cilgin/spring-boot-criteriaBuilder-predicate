//package com.ykb.digital.garage.domain.service.common;
//import com.ykb.digital.garage.common.DateUtil;
//import org.springframework.data.jpa.domain.Specification;
//import javax.persistence.criteria.*;
//import java.lang.reflect.Field;
//import java.text.SimpleDateFormat;
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.util.Date;
//import java.util.List;
//public abstract class AbstractQuerySpecification2<T> implements Specification<T> {
//    protected Object extractFieldValue(String fieldName, boolean isBaseClassField) {
//        try {
//            Field field = this.getClass().getDeclaredField(fieldName);
//            field.setAccessible(true);
//            return field.get(this);
//        } catch (NoSuchFieldException nsfException) {
//            if (isBaseClassField) {
//                throw new RuntimeException(nsfException);
//            }
//            return extractNonBaseClassFieldValue(fieldName);
//        } catch (Exception exc) {
//            throw new RuntimeException(exc);
//        }
//    }
//    protected Date extractDateValue(String fieldName) {
//        Object val = extractFieldValue(fieldName, false);
//        Date dateValue = null;
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//        if (val != null) {
//            try {
//                dateValue = formatter.parse(String.valueOf(val));
//            } catch (Exception e) {
//                return null;
//            }
//        }
//        return dateValue;
//    }
//    protected LocalDateTime extractLocalDateTimeValue(String fieldName) {
//        Object val = extractFieldValue(fieldName, false);
//        LocalDateTime dateValue = null;
//        if (val != null) {
//            try {
//                dateValue = LocalDateTime.parse(String.valueOf(val));
//            } catch (Exception e) {
//                return null;
//            }
//        }
//        return dateValue;
//    }
//    protected boolean addFieldEqualsPredicate(CriteriaBuilder cb, Root<T> root, List<Predicate> predicates,
//                                              String fieldName) {
//        Object value = extractFieldValue(fieldName, false);
//        if (value != null) {
//            predicates.add(cb.equal(root.get(fieldName), value));
//            return true;
//        } else {
//            return false;
//        }
//    }
//    protected boolean addFieldEqualsPredicate(CriteriaBuilder cb, Root<T> root, List<Predicate> predicates,
//                                              String fieldName, String fieldPath) {
//        Object val = extractFieldValue(fieldName, false);
//        if (!isEmpty(val)) {
//            String[] paths = fieldPath.split("[.]");
//            if (paths.length > 1) {
//                Join<Object, Object> current = root.join(paths[0]);
//                for (int i = 1; i < paths.length - 1; ++i) {
//                    current = current.join(paths[i]);
//                }
//                predicates.add(cb.equal(current.get(paths[paths.length - 1]), val));
//            } else {
//                predicates.add(cb.equal(root.get(paths[paths.length - 1]), val));
//            }
//            return true;
//        } else {
//            return false;
//        }
//    }
//    protected Object extractNonBaseClassFieldValue(String fieldName) {
//        try {
//            Field field = this.getClass().getField(fieldName);
//            field.setAccessible(true);
//            return field.get(this);
//        } catch (NoSuchFieldException nsfException) {
//            throw new RuntimeException(nsfException);
//        } catch (Exception exc) {
//            throw new RuntimeException(exc);
//        }
//    }
//    protected boolean addFieldInPredicate(Root<T> root, List<Predicate> predicates,
//                                          String fieldName) {
//        Object value = extractFieldValue(fieldName, false);
//        if (value != null) {
//            Expression<String> exp = root.get(fieldName);
//            Predicate predicate = exp.in((List<Object>) value);
//            predicates.add(predicate);
//            return true;
//        } else {
//            return false;
//        }
//    }
//    protected boolean addDateGreaterThanOrEqualToPredicate(CriteriaBuilder cb, Root<T> root, List<Predicate> predicates,
//                                                           String fieldName, String fieldPath) {
//        Date date = extractDateValue(fieldName);
//        LocalDateTime value = DateUtil.convertLocalDateTimeFromDate(date);
//        if (value == null) return false;
//        predicates.add(cb.greaterThanOrEqualTo(root.get(fieldPath), value));
//        return true;
//    }
//    protected boolean addLocalDateTimeGreaterThanOrEqualToPredicate(CriteriaBuilder cb, Root<T> root, List<Predicate> predicates,
//                                                                    String fieldName, String fieldPath) {
//        LocalDateTime value = extractLocalDateTimeValue(fieldName);
//        if (value != null) {
//            predicates.add(cb.greaterThanOrEqualTo(root.get(fieldPath), value));
//            return true;
//        } else {
//            return false;
//        }
//    }
//    protected boolean addDateLessThanOrEqualToPredicate(CriteriaBuilder cb, Root<T> root, List<Predicate> predicates,
//                                                        String fieldName, String fieldPath) {
//        Date date = extractDateValue(fieldName);
//        LocalDateTime value = DateUtil.convertLocalDateTimeFromDate(date);
//        if (value == null) return false;
//        predicates.add(cb.lessThanOrEqualTo(root.get(fieldPath), value));
//        return true;
//    }
//    private boolean isEmpty(Object val) {
//        if (val instanceof String) {
//            return ((String) val).length() == 0;
//        } else {
//            return val == null;
//        }
//    }
//}