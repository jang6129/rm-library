package com.rmsoft.bookmanagementsystem.dto.borrowrecord;

import com.rmsoft.bookmanagementsystem.entity.Book;
import com.rmsoft.bookmanagementsystem.entity.BorrowRecord;
import com.rmsoft.bookmanagementsystem.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Data
public class BorrowRecordGetResponseDTO {

    private Long recordId;
    private Long userId;
    private Long bookId;
    private Date borrowDate;
    private Date returnDate;

    public BorrowRecordGetResponseDTO(BorrowRecord borrowRecord) {
        this.recordId = borrowRecord.getRecordId();
        this.userId = borrowRecord.getUser().getUserId();
        this.bookId = borrowRecord.getBook().getBookId();
        this.borrowDate = borrowRecord.getBorrowDate();
        this.returnDate = borrowRecord.getReturnDate();
    }
}
