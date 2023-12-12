package com.rmsoft.bookmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "BOOK")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOK_ID")
    private Long bookId;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "AUTHOR", nullable = false)
    private String author;

    @Column(name = "PUBLISHER", nullable = false)
    private String publisher;

    @Column(name = "PUBLISHED_DATE", nullable = false)
    private Date publishedDate;

    @Column(name = "ISBN")
    private String isbn;

    @Column(name = "IS_AVAILABLE")
    private boolean isAvailable = true;

    public Book updateIsAvailable(boolean availability) {
        this.isAvailable = availability;
        return this;
    }
}

