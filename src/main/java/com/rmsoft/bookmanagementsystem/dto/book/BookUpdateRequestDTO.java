package com.rmsoft.bookmanagementsystem.dto.book;

import com.rmsoft.bookmanagementsystem.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class BookUpdateRequestDTO {
    private Long bookId;
    private String title;
    private String author;
    private String publisher;
    private Date publishedDate;
    private String isbn;
    private boolean isAvailable;

    public Book toEntity() {
        return Book.builder()
                .bookId(this.bookId)
                .title(this.title)
                .author(this.author)
                .publisher(this.publisher)
                .publishedDate(this.publishedDate)
                .isbn(this.isbn)
                .isAvailable(this.isAvailable)
                .build();
    }

}
