package com.rmsoft.bookmanagementsystem.service;

import com.rmsoft.bookmanagementsystem.dto.user.UserCreateRequestDTO;
import com.rmsoft.bookmanagementsystem.dto.user.UserCreateResponseDTO;
import com.rmsoft.bookmanagementsystem.entity.User;
import com.rmsoft.bookmanagementsystem.repository.UserRepository;
import com.rmsoft.bookmanagementsystem.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserRepository userRepository = Mockito.mock(UserRepository.class);
    private BCryptPasswordEncoder passwordEncoder = Mockito.mock(BCryptPasswordEncoder.class);
    private UserService userService = new UserService(userRepository, passwordEncoder);

    @Test
    void testCreateUser_Success() {
        // given
        UserCreateRequestDTO request = new UserCreateRequestDTO("testuser", "test@example.com", "strong1234");
        when(userRepository.existsByEmail("test@example.com")).thenReturn(false);
        when(passwordEncoder.encode("strongPassword")).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(new User());

        // when
        UserCreateResponseDTO response = userService.createUser(request);

        // then
        assertEquals("User Registration Successful", response.getMessage());
    }

    @Test
    void testCreateUser_InvalidEmail() {
        // 사전 준비
        UserCreateRequestDTO request = new UserCreateRequestDTO("testuser", "invalidEmail", "strongPassword");

        // 테스트 실행
        UserCreateResponseDTO response = userService.createUser(request);

        // 검증
        assertEquals("Invalid Email", response.getMessage());
    }

    @Test
    void testCreateUser_WeakPassword() {
        // given
        UserCreateRequestDTO request = new UserCreateRequestDTO("testuser", "test@example.com", "weak");

        // when
        UserCreateResponseDTO response = userService.createUser(request);

        // then
        assertEquals("Weak Password", response.getMessage());
    }

    @Test
    void testCreateUser_DuplicateEmail() {
        // given
        User existingUser = User.builder()
                .username("TestUser")
                .email("test@example.com")
                .password("password")
                .build();
        userRepository.save(existingUser);
        UserCreateRequestDTO request = new UserCreateRequestDTO("testuser", "test@example.com", "strong1234");
        when(userRepository.existsByEmail("test@example.com")).thenReturn(true);

        // when
        UserCreateResponseDTO response = userService.createUser(request);

        // then
        assertEquals("Duplicate Email", response.getMessage());
    }
}
