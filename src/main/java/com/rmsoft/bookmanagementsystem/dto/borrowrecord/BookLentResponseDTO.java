package com.rmsoft.bookmanagementsystem.dto.borrowrecord;

import lombok.Data;

@Data
public class BookLentResponseDTO {
    private String message;

    public BookLentResponseDTO(String message) {
        this.message = message;
    }
}
