package com.rmsoft.bookmanagementsystem.service;

import com.rmsoft.bookmanagementsystem.dto.book.BookCreateRequestDTO;
import com.rmsoft.bookmanagementsystem.dto.book.BookCreateResponseDTO;
import com.rmsoft.bookmanagementsystem.dto.book.BookUpdateRequestDTO;
import com.rmsoft.bookmanagementsystem.dto.book.BookUpdateResponseDTO;
import com.rmsoft.bookmanagementsystem.dto.borrowrecord.*;
import com.rmsoft.bookmanagementsystem.dto.user.UserCreateRequestDTO;
import com.rmsoft.bookmanagementsystem.dto.user.UserCreateResponseDTO;
import com.rmsoft.bookmanagementsystem.service.book.BookService;
import com.rmsoft.bookmanagementsystem.service.borrowrecord.BorrowRecordService;
import com.rmsoft.bookmanagementsystem.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceFacade {

    private final UserService userService;
    private final BookService bookService;
    private final BorrowRecordService borrowRecordService;

    @Autowired
    public ServiceFacade(UserService userService, BookService bookService, BorrowRecordService borrowRecordService) {
        this.userService = userService;
        this.bookService = bookService;
        this.borrowRecordService = borrowRecordService;
    }

    public UserCreateResponseDTO createUser(UserCreateRequestDTO userCreateRequestDTO) {
        return userService.createUser(userCreateRequestDTO);
    }

    public BookCreateResponseDTO createBook(BookCreateRequestDTO bookCreateRequestDTO) {
        return bookService.createBook(bookCreateRequestDTO);
    }

    public BookUpdateResponseDTO updateBook(BookUpdateRequestDTO bookUpdateRequestDTO) {
        return bookService.updateBook(bookUpdateRequestDTO);
    }

    public List<BorrowRecordGetResponseDTO> getBorrowRecordsByBookId(BorrowRecordGetRequestDTO borrowRecordGetRequestDTO) {
        return borrowRecordService.getBorrowRecordsByBookId(borrowRecordGetRequestDTO);
    }

    public BookLentResponseDTO lendBookToUser(BookLentRequestDTO bookLentRequestDTO) {
        return borrowRecordService.lendBookToUser(bookLentRequestDTO);
    }

    public BookReturnResponseDTO returnBookByBookId(BookReturnRequestDTO bookReturnRequestDTO) {
        return borrowRecordService.returnBookByBookId(bookReturnRequestDTO);
    }

}
