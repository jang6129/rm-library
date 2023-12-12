package com.rmsoft.bookmanagementsystem.service.book;

import com.rmsoft.bookmanagementsystem.dto.book.BookCreateRequestDTO;
import com.rmsoft.bookmanagementsystem.dto.book.BookCreateResponseDTO;
import com.rmsoft.bookmanagementsystem.dto.book.BookUpdateRequestDTO;
import com.rmsoft.bookmanagementsystem.dto.book.BookUpdateResponseDTO;
import com.rmsoft.bookmanagementsystem.entity.Book;
import com.rmsoft.bookmanagementsystem.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    public BookCreateResponseDTO createBook(BookCreateRequestDTO bookCreateRequestDTO) {
        Book book = bookCreateRequestDTO.toEntity();

        Book registeredBook = bookRepository.save(book);

        if (registeredBook != null) {
            return new BookCreateResponseDTO("Book Registration Successful");
        } else {
            return new BookCreateResponseDTO("Book Registration Failed");
        }
    }

    @Transactional
    public BookUpdateResponseDTO updateBook(BookUpdateRequestDTO bookUpdateRequestDTO) {
        Book book = bookUpdateRequestDTO.toEntity();

        if (bookRepository.existsById(book.getBookId())) {
            Book registeredBook = bookRepository.save(book);
            if (registeredBook != null) {
                return new BookUpdateResponseDTO("Book Update Successful");
            } else {
                throw new RuntimeException("Book Update Failed");
            }
        } else {
            throw new EntityNotFoundException("Book Not Found");
        }

    }


}
