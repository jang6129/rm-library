package com.rmsoft.bookmanagementsystem.service.borrowrecord;

import com.rmsoft.bookmanagementsystem.dto.borrowrecord.*;
import com.rmsoft.bookmanagementsystem.entity.Book;
import com.rmsoft.bookmanagementsystem.entity.BorrowRecord;
import com.rmsoft.bookmanagementsystem.entity.User;
import com.rmsoft.bookmanagementsystem.repository.BookRepository;
import com.rmsoft.bookmanagementsystem.repository.BorrowRecordRepository;
import com.rmsoft.bookmanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowRecordService {

    private final Integer PAGE_SIZE = 20;
    private final BorrowRecordRepository borrowRecordRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Autowired
    public BorrowRecordService(BorrowRecordRepository borrowRecordRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.borrowRecordRepository = borrowRecordRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public List<BorrowRecordGetResponseDTO> getBorrowRecordsByBookId(BorrowRecordGetRequestDTO borrowRecordGetRequestDTO) {
        Pageable pageable = PageRequest.of(borrowRecordGetRequestDTO.getPage(), PAGE_SIZE);

        Page<BorrowRecord> borrowRecords = borrowRecordRepository.findByBook_BookId(borrowRecordGetRequestDTO.getBookId(), pageable);

        List<BorrowRecordGetResponseDTO> response = borrowRecords.getContent()
                .stream()
                .map(BorrowRecordGetResponseDTO::new)
                .collect(Collectors.toList());

        return response;
    }

    @Transactional
    public BookLentResponseDTO lendBookToUser(BookLentRequestDTO bookLentRequestDTO) {
        Long bookId = bookLentRequestDTO.getBookId();
        Long userId = bookLentRequestDTO.getUserId();

        // 책 대출 가능 여부 확인
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + bookId));

        if (!book.isAvailable()) {
            throw new RuntimeException("The Book is not available");
        }

        // 사용자 확인
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        // 대출 기록 생성 및 저장
        BorrowRecord borrowRecord = BorrowRecord.builder()
                .book(book)
                .user(user)
                .borrowDate(new Date())
                .build();
        BorrowRecord writtenRecord = borrowRecordRepository.save(borrowRecord);

        // 책 상태 업데이트
        book.updateIsAvailable(false);
        bookRepository.save(book);

        if (writtenRecord != null) {
            return new BookLentResponseDTO("Book Lent Successful");
        } else {
            throw new RuntimeException("Book Lent Failed");
        }
    }

    @Transactional
    public BookReturnResponseDTO returnBookByBookId(BookReturnRequestDTO bookReturnRequestDTO) {
        Long bookId = bookReturnRequestDTO.getBookId();

        // 최신 대출 기록 확인
        BorrowRecord latestBorrowRecord = borrowRecordRepository.findTopByBook_BookIdOrderByBorrowDateDesc(bookId)
                .orElseThrow(() -> new RuntimeException("No active borrow record found for bookId: " + bookId));

        // 최신 기록의 반납 날짜가 null 인지 확인
        if (latestBorrowRecord.getReturnDate() != null) {
            throw new RuntimeException("The book with bookId: " + bookId + " has already been returned.");
        }

        // BORROW_RECORD 에 반납 기록 기입
        latestBorrowRecord.updateReturnDate(new Date());
        borrowRecordRepository.save(latestBorrowRecord);

        // BOOK 에 isAvailable 기입
        Book returnedBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("No borrowed book found for bookId: " + bookId));
        returnedBook.updateIsAvailable(true);
        bookRepository.save(returnedBook);

        return new BookReturnResponseDTO("Book Return Successful");
    }


}
