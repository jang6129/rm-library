package com.rmsoft.bookmanagementsystem.controller;

import com.rmsoft.bookmanagementsystem.dto.borrowrecord.*;
import com.rmsoft.bookmanagementsystem.service.ServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/borrow-records")
public class BorrowRecordController {
    private final ServiceFacade serviceFacade;

    @Autowired
    public BorrowRecordController(ServiceFacade serviceFacade) {
        this.serviceFacade = serviceFacade;
    }

    @GetMapping
    public ResponseEntity<List<BorrowRecordGetResponseDTO>> getBorrowRecordsByBookId(BorrowRecordGetRequestDTO borrowRecordGetRequestDTO) {
        return ResponseEntity.ok(serviceFacade.getBorrowRecordsByBookId(borrowRecordGetRequestDTO));
    }

    @PutMapping("/lend")
    public ResponseEntity<BookLentResponseDTO> lendBookToUser(BookLentRequestDTO bookLentRequestDTO) {
        return ResponseEntity.ok(serviceFacade.lendBookToUser(bookLentRequestDTO));
    }

    @PutMapping("/return")
    public ResponseEntity<BookReturnResponseDTO> returnBookByBookId(BookReturnRequestDTO bookReturnRequestDTO) {
        return ResponseEntity.ok(serviceFacade.returnBookByBookId(bookReturnRequestDTO));
    }

}
