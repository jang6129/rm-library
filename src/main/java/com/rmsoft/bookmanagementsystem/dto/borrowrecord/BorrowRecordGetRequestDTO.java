package com.rmsoft.bookmanagementsystem.dto.borrowrecord;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BorrowRecordGetRequestDTO {
    private Long bookId;
    private Integer page;
}
