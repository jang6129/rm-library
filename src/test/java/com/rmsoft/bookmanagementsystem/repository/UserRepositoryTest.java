package com.rmsoft.bookmanagementsystem.repository;

import com.rmsoft.bookmanagementsystem.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveUser() {
        User newUser = User.builder()
                .username("TestUser")
                .email("testuser@example.com")
                .password("password")
                .build();

        User savedUser = userRepository.save(newUser);
        assertNotNull(savedUser.getUserId());
        assertEquals("TestUser", savedUser.getUsername());
        assertEquals("testuser@example.com", savedUser.getEmail());
    }

    @Test
    public void testExistsByEmail() {
        User newUser = User.builder()
                .username("TestUser")
                .email("testuser@example.com")
                .password("password")
                .build();
        userRepository.save(newUser);

        boolean exists = userRepository.existsByEmail("testuser@example.com");
        assertTrue(exists);

        exists = userRepository.existsByEmail("nonexistent@example.com");
        assertFalse(exists);
    }

}
