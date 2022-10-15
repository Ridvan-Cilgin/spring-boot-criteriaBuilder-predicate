package com.ridvancilgin.springbootcriteriabuilderpredicate.service;

import com.ridvancilgin.springbootcriteriabuilderpredicate.dto.BookDto;
import com.ridvancilgin.springbootcriteriabuilderpredicate.entity.Book;
import com.ridvancilgin.springbootcriteriabuilderpredicate.error.BookNotFoundException;
import com.ridvancilgin.springbootcriteriabuilderpredicate.repository.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    @DisplayName("Should throw an exception when the id is invalid")
    void deleteBookWhenIdIsInvalidThenThrowException() {
        Long id = 1L;
        when(bookRepository.findById(any())).thenReturn(null);
        assertThrows(Exception.class, () -> bookService.findOneBook(id));
    }
//
//    @Test
//    @DisplayName("Should delete the book when the id is valid")
//    void deleteBookWhenIdIsValid() {
//        Book book =
//                Book.builder()
//                        .id(1L)
//                        .name("Java")
//                        .author("James")
//                        .price(new BigDecimal(100))
//                        .build();
//        when(bookRepository.findById(any())).thenReturn(java.util.Optional.of(book));
//        bookService.deleteBook(1L);
//        verify(bookRepository, times(1)).deleteById(any());
//    }

    @Test
    @DisplayName("Should throw an exception when the book is not found")
    void patchAuthorWhenBookIsNotFoundThenThrowException() {
        Long id = 1L;
        when(bookRepository.findById(any())).thenReturn(java.util.Optional.empty());
        assertThrows(BookNotFoundException.class, () -> bookService.patchAuthor(null, id));
    }

//    @Test
//    @DisplayName("Should update the author when the book is found")
//    void patchAuthorWhenBookIsFoundThenUpdateAuthor() {
//        Book book =
//                Book.builder()
//                        .id(1L)
//                        .name("Java")
//                        .author("Ridvan")
//                        .price(new BigDecimal(100))
//                        .build();
//
//        when(bookRepository.findById(1L)).thenReturn(java.util.Optional.of(book));
//
//        Map<String, String> updateRequest = new HashMap<>();
//        updateRequest.put("author", "UpdatedAuthor");
//        Book updatedBook = bookService.patchAuthor(updateRequest, 1L);
//
//        assertEquals("UpdatedAuthor", updatedBook.getAuthor());
//    }

    @Test
    @DisplayName("Should create a new book when the book is not found")
    void updateBookWhenBookIsNotFoundThenCreateNewOne() {
        Book book =
                Book.builder()
                        .id(1L)
                        .name("Java")
                        .author("Ridvan")
                        .price(new BigDecimal(100))
                        .build();
        when(bookRepository.findById(any())).thenReturn(java.util.Optional.empty());
        when(bookRepository.save(any())).thenReturn(book);

        Book updatedBook = bookService.updateBook(book, 1L);

        assertEquals(book, updatedBook);
    }

//    @Test
//    @DisplayName("Should update the book when the book is found")
//    void updateBookWhenBookIsFound() {
//        Book book =
//                Book.builder()
//                        .id(1L)
//                        .name("Java")
//                        .author("Ridvan")
//                        .price(new BigDecimal(100))
//                        .build();
//
//        when(bookRepository.findById(1L)).thenReturn(java.util.Optional.of(book));
//
//        Book updatedBook = bookService.updateBook(book, 1L);
//
//        assertEquals(book, updatedBook);
//    }

    @Test
    @DisplayName("Should throw an exception when the id is not found")
    void findOneBookWhenIdIsNotFoundThenThrowException() {
        when(bookRepository.findById(any())).thenReturn(java.util.Optional.empty());
        assertThrows(Exception.class, () -> bookService.findOneBook(1L));
    }

    @Test
    @DisplayName("Should return the book when the id is found")
    void findOneBookWhenIdIsFound() throws Exception {
        Book book =
                Book.builder()
                        .id(1L)
                        .name("Java")
                        .author("James Gosling")
                        .price(new BigDecimal(100))
                        .build();

        when(bookRepository.findById(any())).thenReturn(java.util.Optional.of(book));

        BookDto bookDto = bookService.findOneBook(1L);

        assertEquals(bookDto.getId(), book.getId());
        assertEquals(bookDto.getName(), book.getName());
        assertEquals(bookDto.getAuthor(), book.getAuthor());
        assertEquals(bookDto.getPrice(), book.getPrice());
    }

    @Test
    @DisplayName("Should return the book when the book is created")
    void createBookWhenBookIsCreatedThenReturnTheBook() {
        BookDto bookDto =
                BookDto.builder()
                        .id(1L)
                        .name("Java")
                        .author("Ridvan")
                        .price(new BigDecimal(100))
                        .build();

        Book book =
                Book.builder()
                        .id(1L)
                        .name("Java")
                        .author("Ridvan")
                        .price(new BigDecimal(100))
                        .build();

        when(bookRepository.save(any())).thenReturn(book);

        BookDto createdBook = bookService.createBook(bookDto);

        assertEquals(createdBook.getId(), bookDto.getId());
        assertEquals(createdBook.getName(), bookDto.getName());
        assertEquals(createdBook.getAuthor(), bookDto.getAuthor());
        assertEquals(createdBook.getPrice(), bookDto.getPrice());
    }

    @Test
    @DisplayName("Should return all books")
    void findAllBookShouldReturnAllBooks() {
        Book book1 =
                Book.builder()
                        .id(1L)
                        .name("Book1")
                        .author("Author1")
                        .price(new BigDecimal(10))
                        .build();
        Book book2 =
                Book.builder()
                        .id(2L)
                        .name("Book2")
                        .author("Author2")
                        .price(new BigDecimal(20))
                        .build();
        List<Book> books = Arrays.asList(book1, book2);
        when(bookRepository.findAll()).thenReturn(books);

        List<BookDto> bookDtos = bookService.findAllBook();

        assertEquals(2, bookDtos.size());
        assertEquals("Book1", bookDtos.get(0).getName());
        assertEquals("Author1", bookDtos.get(0).getAuthor());
        assertEquals(new BigDecimal(10), bookDtos.get(0).getPrice());
        assertEquals("Book2", bookDtos.get(1).getName());
        assertEquals("Author2", bookDtos.get(1).getAuthor());
        assertEquals(new BigDecimal(20), bookDtos.get(1).getPrice());
    }
}