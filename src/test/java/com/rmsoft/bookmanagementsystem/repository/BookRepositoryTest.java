package com.rmsoft.bookmanagementsystem.repository;

import com.rmsoft.bookmanagementsystem.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    private Book createSampleBook() {
        return Book.builder()
                .title("Sample Book")
                .author("Author Name")
                .publisher("Publisher Name")
                .publishedDate(new Date())
                .isbn("1234567890")
                .build();
    }

    @Test
    public void testSaveBook() {
        Book book = createSampleBook();

        Book savedBook = bookRepository.save(book);
        assertNotNull(savedBook.getBookId());
        assertEquals("Sample Book", savedBook.getTitle());
    }

}
