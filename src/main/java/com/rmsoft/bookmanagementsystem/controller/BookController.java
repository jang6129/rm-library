package com.rmsoft.bookmanagementsystem.controller;

import com.rmsoft.bookmanagementsystem.dto.book.BookCreateRequestDTO;
import com.rmsoft.bookmanagementsystem.dto.book.BookCreateResponseDTO;
import com.rmsoft.bookmanagementsystem.dto.book.BookUpdateRequestDTO;
import com.rmsoft.bookmanagementsystem.dto.book.BookUpdateResponseDTO;
import com.rmsoft.bookmanagementsystem.service.ServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final ServiceFacade serviceFacade;

    @Autowired
    public BookController(ServiceFacade serviceFacade) {
        this.serviceFacade = serviceFacade;
    }

    @PostMapping("/register")
    public ResponseEntity<BookCreateResponseDTO> createBook(@RequestBody BookCreateRequestDTO bookCreateRequestDTO) {
        return ResponseEntity.ok(serviceFacade.createBook(bookCreateRequestDTO));
    }

    @PutMapping("/update")
    public ResponseEntity<BookUpdateResponseDTO> updateBook(@RequestBody BookUpdateRequestDTO bookUpdateRequestDTO) {
        return ResponseEntity.ok(serviceFacade.updateBook(bookUpdateRequestDTO));
    }



}
