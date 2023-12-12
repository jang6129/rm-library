package com.rmsoft.bookmanagementsystem.dto.book;

import com.rmsoft.bookmanagementsystem.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class BookCreateRequestDTO {
    private String title;
    private String author;
    private String publisher;
    private Date publishedDate;
    private String isbn;

    public Book toEntity() {
        return Book.builder()
                .title(this.title)
                .author(this.author)
                .publisher(this.publisher)
                .publishedDate(this.publishedDate)
                .isbn(this.isbn)
                .build();
    }

}
