package com.rmsoft.bookmanagementsystem.dto.borrowrecord;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookLentRequestDTO {
    private Long userId;
    private Long bookId;
}
