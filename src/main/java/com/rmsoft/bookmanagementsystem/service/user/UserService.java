package com.rmsoft.bookmanagementsystem.service.user;

import com.rmsoft.bookmanagementsystem.dto.user.UserCreateRequestDTO;
import com.rmsoft.bookmanagementsystem.dto.user.UserCreateResponseDTO;
import com.rmsoft.bookmanagementsystem.entity.User;
import com.rmsoft.bookmanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserCreateResponseDTO createUser(UserCreateRequestDTO userCreateRequestDTO) {

        if (!isValidEmail(userCreateRequestDTO.getEmail())) {
            return new UserCreateResponseDTO("Invalid Email");
        }
        if (!isStrongPassword(userCreateRequestDTO.getPassword())) {
            return new UserCreateResponseDTO("Weak Password");
        }
        if (userRepository.existsByEmail(userCreateRequestDTO.getEmail())) {
            return new UserCreateResponseDTO("Duplicate Email");
        }

        // password encoding
        String encodedPassword = passwordEncoder.encode(userCreateRequestDTO.getPassword());
        userCreateRequestDTO.setPassword(encodedPassword);
        User user = userCreateRequestDTO.toEntity();

        User registeredUser = userRepository.save(user);

        if (registeredUser != null) {
            return new UserCreateResponseDTO("User Registration Successful");
        } else {
            throw new RuntimeException("User Registration Failed");
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    private boolean isStrongPassword(String password) {
        return password.length() >= 8 &&
                password.matches(".*[a-zA-Z].*") &&
                password.matches(".*[0-9].*");
    }


}
