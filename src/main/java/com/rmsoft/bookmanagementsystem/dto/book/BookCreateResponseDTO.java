package com.rmsoft.bookmanagementsystem.dto.book;

import lombok.Data;

@Data
public class BookCreateResponseDTO {
    private String message;

    public BookCreateResponseDTO(String message) {
        this.message = message;
    }
}
