package com.rmsoft.bookmanagementsystem.service;

import com.rmsoft.bookmanagementsystem.dto.borrowrecord.*;
import com.rmsoft.bookmanagementsystem.entity.Book;
import com.rmsoft.bookmanagementsystem.entity.BorrowRecord;
import com.rmsoft.bookmanagementsystem.entity.User;
import com.rmsoft.bookmanagementsystem.repository.BookRepository;
import com.rmsoft.bookmanagementsystem.repository.BorrowRecordRepository;
import com.rmsoft.bookmanagementsystem.repository.UserRepository;
import com.rmsoft.bookmanagementsystem.service.borrowrecord.BorrowRecordService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BorrowRecordServiceTest {

    private BorrowRecordRepository borrowRecordRepository = mock(BorrowRecordRepository.class);
    private BookRepository bookRepository = mock(BookRepository.class);
    private UserRepository userRepository = mock(UserRepository.class);

    private BorrowRecordService borrowRecordService =
            new BorrowRecordService(borrowRecordRepository, bookRepository, userRepository);

    @Test
    void testLendBookToUser() {
        // given
        Book book = new Book(1L, "Book 1", "Author 1", "Publisher 1", new Date(), "ISBN", true);
        User user = new User(1L, "User 1", "user1@example.com", "password", new Date());
        BorrowRecord record = new BorrowRecord(1L, user, book, new Date(), null);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(borrowRecordRepository.save(Mockito.any(BorrowRecord.class))).thenReturn(record);

        // when
        BookLentResponseDTO response = borrowRecordService.lendBookToUser(new BookLentRequestDTO(1L, 1L));

        // then
        assertEquals("Book Lent Successful", response.getMessage());
        assertFalse(book.isAvailable());
    }

    @Test
    void testGetBorrowRecordsByBookId() {
        // given
        Book book = new Book(1L, "Book 1", "Author 1", "Publisher 1", new Date(), "ISBN", true);
        User user = new User(1L, "User 1", "user1@example.com", "password", new Date());
        BorrowRecord record = new BorrowRecord(1L, user, book, new Date(), null);
        Page<BorrowRecord> page = new PageImpl<>(Arrays.asList(record));

        when(borrowRecordRepository.findByBook_BookId(eq(1L), any(PageRequest.class))).thenReturn(page);

        // when
        List<BorrowRecordGetResponseDTO> response = borrowRecordService.getBorrowRecordsByBookId(new BorrowRecordGetRequestDTO(1L, 0));

        // then
        assertFalse(response.isEmpty());
        assertEquals(1, response.size());
    }

    @Test
    void testReturnBookByBookId() {
        // given
        Book book = new Book(1L, "Book 1", "Author 1", "Publisher 1", new Date(), "ISBN", false);
        User user = new User(1L, "User 1", "user1@example.com", "password", new Date());
        BorrowRecord record = new BorrowRecord(1L, user, book, new Date(), null);

        when(bookRepository.findById(book.getBookId())).thenReturn(Optional.of(book));
        when(borrowRecordRepository.findTopByBook_BookIdOrderByBorrowDateDesc(1L)).thenReturn(Optional.of(record));

        // when
        BookReturnResponseDTO response = borrowRecordService.returnBookByBookId(new BookReturnRequestDTO(1L));

        // then
        assertEquals("Book Return Successful", response.getMessage());
        assertNotNull(record.getReturnDate());
        assertTrue(book.isAvailable());
    }

}


