package com.rmsoft.bookmanagementsystem.controller;

import com.rmsoft.bookmanagementsystem.dto.user.UserCreateRequestDTO;
import com.rmsoft.bookmanagementsystem.dto.user.UserCreateResponseDTO;
import com.rmsoft.bookmanagementsystem.service.ServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final ServiceFacade serviceFacade;

    @Autowired
    public UserController(ServiceFacade serviceFacade) {
        this.serviceFacade = serviceFacade;
    }

    @PostMapping("/register")
    ResponseEntity<UserCreateResponseDTO> createUser(@RequestBody UserCreateRequestDTO userCreateRequestDTO) {
        return ResponseEntity.ok(serviceFacade.createUser(userCreateRequestDTO));
    }

}
