package com.rmsoft.bookmanagementsystem.dto.borrowrecord;

import lombok.Data;

@Data
public class BookReturnResponseDTO {
    private String message;

    public BookReturnResponseDTO(String message) {
        this.message = message;
    }
}
