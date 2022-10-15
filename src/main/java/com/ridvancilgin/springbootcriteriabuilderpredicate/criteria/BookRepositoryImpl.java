//package com.ridvancilgin.springbootcriteriabuilderpredicate.criteria;
//
//import com.ridvancilgin.springbootcriteriabuilderpredicate.entity.Book;
//import com.ridvancilgin.springbootcriteriabuilderpredicate.repository.BookRepository;
//import com.ridvancilgin.springbootcriteriabuilderpredicate.repository.specification.BookSpec;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//import java.util.List;
//
//public class BookRepositoryImpl implements BookRepository {
//
//    private EntityManager entityManager;
//
//    public BookRepositoryImpl() {
//        super();
//        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-h2-criteria");
//        entityManager = factory.createEntityManager();
//    }
//
//    @Override
//    public List<Book> findBookByNameAndPrice() {
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        //Book un <T> şeklinde generic olması lazım -------> ROOT
//        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
//        Root<Book> bookRoot = criteriaQuery.from(Book.class);
//
////        //burayı parametrik yapıcaz--------------> criteriaBuilder CB
////        Predicate predicateForBlueColor = criteriaBuilder.equal(bookRoot.get("color"), "blue");
////        Predicate predicateForRedColor = criteriaBuilder.equal(bookRoot.get("color"), "red");
////        Predicate predicateForColor = criteriaBuilder.or(predicateForBlueColor, predicateForRedColor);
////
////        Predicate predicateForGradeA = criteriaBuilder.equal(bookRoot.get("grade"), "A");
////        Predicate predicateForGradeB = criteriaBuilder.equal(bookRoot.get("grade"), "B");
////        Predicate predicateForGrade = criteriaBuilder.or(predicateForGradeA, predicateForGradeB);
////
////        // final search filter
////        Predicate finalPredicate = criteriaBuilder.and(predicateForColor, predicateForGrade);
//        BookSpec bookSpec = new BookSpec();
//        Predicate finalPredicate = bookSpec.toPredicate(bookRoot, criteriaQuery, criteriaBuilder);
//        criteriaQuery.where(finalPredicate);
//
//        List<Book> bookList = entityManager.createQuery(criteriaQuery)
//                .getResultList();
//        return bookList;
//    }
//
//
//    ;
//
//    @Override
//    public List<Book> findBookByAuthorOrPrice() {
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
//        Root<Book> bookRoot = criteriaQuery.from(Book.class);
//
//        Predicate predicateForBlueColor = criteriaBuilder.equal(bookRoot.get("color"), "red");
//        Predicate predicateForGradeA = criteriaBuilder.equal(bookRoot.get("grade"), "D");
//        Predicate predicateForBlueColorAndGradeA = criteriaBuilder.and(predicateForBlueColor, predicateForGradeA);
//
//        Predicate predicateForRedColor = criteriaBuilder.equal(bookRoot.get("color"), "blue");
//        Predicate predicateForGradeB = criteriaBuilder.equal(bookRoot.get("grade"), "B");
//        Predicate predicateForRedColorAndGradeB = criteriaBuilder.and(predicateForRedColor, predicateForGradeB);
//
//        // final search filter
//        Predicate finalPredicate = criteriaBuilder.or(predicateForBlueColorAndGradeA, predicateForRedColorAndGradeB);
//
//        criteriaQuery.where(finalPredicate);
//
//        List<Book> items = entityManager.createQuery(criteriaQuery)
//                .getResultList();
//        return items;
//    }
//}
