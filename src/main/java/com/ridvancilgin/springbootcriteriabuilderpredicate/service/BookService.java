package com.ridvancilgin.springbootcriteriabuilderpredicate.service;


import com.ridvancilgin.springbootcriteriabuilderpredicate.dto.BookDto;
import com.ridvancilgin.springbootcriteriabuilderpredicate.entity.Book;
import com.ridvancilgin.springbootcriteriabuilderpredicate.error.BookNotFoundException;
import com.ridvancilgin.springbootcriteriabuilderpredicate.error.BookUnSupportedFieldPatchException;
import com.ridvancilgin.springbootcriteriabuilderpredicate.repository.BookRepository;
import com.ridvancilgin.springbootcriteriabuilderpredicate.repository.specification.BookSpec;
import lombok.RequiredArgsConstructor;
import org.dozer.DozerBeanMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.apache.logging.log4j.util.Strings.isBlank;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();

    public List<BookDto> findAllBook() {
        List<Book> bookDtoList = bookRepository.findAll();
        return bookDtoList.stream().map(book ->
                dozerBeanMapper.map(book, BookDto.class)).collect(Collectors.toList());
    }

    public BookDto createBook(BookDto bookDto) {
        Book book = bookRepository.save(dozerBeanMapper.map(bookDto, Book.class));
        return dozerBeanMapper.map(book, BookDto.class);
    }

    public BookDto findOneBook(Long id) throws Exception {
        Book book = bookRepository.findById(id).orElseThrow(Exception::new);
        return dozerBeanMapper.map(book, BookDto.class);
    }

    public Book updateBook(Book book, Long id) {
        return bookRepository.findById(id).map(x -> {
            x.setName(book.getName());
            x.setAuthor(book.getAuthor());
            x.setPrice(book.getPrice());
            return bookRepository.save(x);
        }).orElseGet(() -> {
            book.setId(id);
            return bookRepository.save(book);
        });
    }

    public Book patchAuthor(Map<String, String> update, Long id) {
        return bookRepository.findById(id)
                .map(x -> {
                    String author = update.get("author");
                    if (!isBlank(author)) {
                        x.setAuthor(author);
                        return bookRepository.save(x);
                    } else {
                        throw new BookUnSupportedFieldPatchException(update.keySet());
                    }
                }).orElseThrow(() -> new BookNotFoundException(id));
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> filterBooks(BookDto request) {
        BookSpec bookSpec = new BookSpec();
        bookSpec.setId(request.getId());
        bookSpec.setName(request.getName());
        bookSpec.setAuthor(request.getAuthor());
        bookSpec.setPrice(request.getPrice());

        return bookRepository.findAll(bookSpec);
    }

}