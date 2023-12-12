package com.rmsoft.bookmanagementsystem.dto.user;

import com.rmsoft.bookmanagementsystem.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserCreateRequestDTO {
    private String username;
    private String email;
    private String password;

    public User toEntity() {
        return User.builder()
                .username(this.username)
                .email(this.email)
                .password(this.password)
                .build();
    }

}
