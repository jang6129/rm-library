package com.rmsoft.bookmanagementsystem.repository;

import com.rmsoft.bookmanagementsystem.entity.Book;
import com.rmsoft.bookmanagementsystem.entity.BorrowRecord;
import com.rmsoft.bookmanagementsystem.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BorrowRecordRepositoryTest {

    @Autowired
    private BorrowRecordRepository borrowRecordRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    private User createSampleUser() {
        return new User(null, "testuser", "test@example.com", "password", new Date());
    }

    private Book createSampleBook() {
        return new Book(null, "Sample Book", "Author Name", "Publisher", new Date(), "1234567890", true);
    }

    @Test
    public void testSaveBorrowRecord() {
        User user = userRepository.save(createSampleUser());
        Book book = bookRepository.save(createSampleBook());

        BorrowRecord record = BorrowRecord.builder()
                .user(user)
                .book(book)
                .borrowDate(new Date())
                .build();

        BorrowRecord savedRecord = borrowRecordRepository.save(record);
        assertNotNull(savedRecord.getRecordId());
        assertEquals(user.getUserId(), savedRecord.getUser().getUserId());
        assertEquals(book.getBookId(), savedRecord.getBook().getBookId());
    }

    @Test
    public void testFindByBook_BookId() {
        User user = userRepository.save(createSampleUser());
        Book book = bookRepository.save(createSampleBook());

        borrowRecordRepository.save(BorrowRecord.builder()
                .user(user)
                .book(book)
                .borrowDate(new Date())
                .build());

        Page<BorrowRecord> records = borrowRecordRepository.findByBook_BookId(book.getBookId(), PageRequest.of(0, 10));
        assertFalse(records.isEmpty());
    }

    @Test
    public void testFindTopByBook_BookIdOrderByBorrowDateDesc() {
        User user = userRepository.save(createSampleUser());
        Book book = bookRepository.save(createSampleBook());

        BorrowRecord record = BorrowRecord.builder()
                .user(user)
                .book(book)
                .borrowDate(new Date())
                .build();
        borrowRecordRepository.save(record);

        Optional<BorrowRecord> foundRecord = borrowRecordRepository.findTopByBook_BookIdOrderByBorrowDateDesc(book.getBookId());
        assertTrue(foundRecord.isPresent());
        assertEquals(record.getRecordId(), foundRecord.get().getRecordId());
    }

}
