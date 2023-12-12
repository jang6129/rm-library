package com.rmsoft.bookmanagementsystem.dto.user;

import lombok.Data;

@Data
public class UserCreateResponseDTO {
    private String message;

    public UserCreateResponseDTO(String message) {
        this.message = message;
    }
}
