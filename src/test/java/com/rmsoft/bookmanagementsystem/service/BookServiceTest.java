package com.rmsoft.bookmanagementsystem.service;

import com.rmsoft.bookmanagementsystem.dto.book.BookCreateRequestDTO;
import com.rmsoft.bookmanagementsystem.dto.book.BookCreateResponseDTO;
import com.rmsoft.bookmanagementsystem.dto.book.BookUpdateRequestDTO;
import com.rmsoft.bookmanagementsystem.dto.book.BookUpdateResponseDTO;
import com.rmsoft.bookmanagementsystem.entity.Book;
import com.rmsoft.bookmanagementsystem.repository.BookRepository;
import com.rmsoft.bookmanagementsystem.service.book.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    private BookRepository bookRepository = Mockito.mock(BookRepository.class);
    private BookService bookService = new BookService(bookRepository);

    @Test
    void testCreateBook() {
        // given
        Book book = new Book(null, "Test Book", "Author", "Publisher", new Date(), "1234567890", true);
        BookCreateRequestDTO request = new BookCreateRequestDTO("Test Book", "Author", "Publisher", new Date(), "1234567890");

        Mockito.when(bookRepository.save(Mockito.any(Book.class))).thenReturn(book);

        // when
        BookCreateResponseDTO response = bookService.createBook(request);

        // then
        assertEquals("Book Registration Successful", response.getMessage());
    }

    @Test
    void testUpdateBook() {
        // given
        Book book = new Book(1L, "Updated Test Book", "Author", "Publisher", new Date(), "1234567890", true);
        BookUpdateRequestDTO request = new BookUpdateRequestDTO(1L, "Updated Test Book", "Author", "Publisher", new Date(), "1234567890", true);

        Mockito.when(bookRepository.existsById(1L)).thenReturn(true);
        Mockito.when(bookRepository.save(Mockito.any(Book.class))).thenReturn(book);

        // when
        BookUpdateResponseDTO response = bookService.updateBook(request);

        // then
        assertEquals("Book Update Successful", response.getMessage());
    }

}

