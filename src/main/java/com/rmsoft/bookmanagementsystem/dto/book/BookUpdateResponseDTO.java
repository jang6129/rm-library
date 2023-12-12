package com.rmsoft.bookmanagementsystem.dto.book;

import lombok.Data;

@Data
public class BookUpdateResponseDTO {
    private String message;

    public BookUpdateResponseDTO(String message) {
        this.message = message;
    }

}
