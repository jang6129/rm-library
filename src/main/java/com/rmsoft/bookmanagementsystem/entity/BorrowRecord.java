package com.rmsoft.bookmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "BORROW_RECORD")
public class BorrowRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RECORD_ID")
    private Long recordId;

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "BOOK_ID", referencedColumnName = "BOOK_ID")
    private Book book;

    @Column(name = "BORROW_DATE", nullable = false)
    private Date borrowDate;

    @Column(name = "RETURN_DATE")
    private Date returnDate;

    public BorrowRecord updateReturnDate(Date date) {
        this.returnDate = date;
        return this;
    }

}
