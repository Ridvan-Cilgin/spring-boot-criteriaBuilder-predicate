package com.ridvancilgin.springbootcriteriabuilderpredicate.repository;


import com.ridvancilgin.springbootcriteriabuilderpredicate.entity.Book;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    List<Book> findAll(Specification<Book> spec);

//    List<Book> findBookByNameAndPrice();
//
//    List<Book> findBookByAuthorOrPrice();
}