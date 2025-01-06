package com.swipebyte.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.swipebyte.project.dto.*;
import java.util.*;
import com.swipebyte.project.entity.*;

import com.swipebyte.project.repository.UserRepository;

@RestController
public class LoginController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder encoder;

    public UserEntity findByUsernameorEmail(String username, String email) {

        UserEntity user = null;

        if (username == null) {
            user = userRepo.findByEmail(email);
        } else {
            user = userRepo.findByUsername(username);
        }

        return user;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto login) {

        String input_user = login.getUsername() != null ? login.getUsername() : null;
        String input_pass = login.getPassword();
        String input_email = login.getEmail() != null ? login.getEmail() : null;

        UserEntity user = findByUsernameorEmail(input_user, input_email);

        if (user == null) {
            return ResponseEntity.badRequest().body(Map.of("message", "This user does not exist."));
        }

        if (encoder.matches(input_pass, user.getPassword())) {
            return ResponseEntity.ok(Map.of(
                    "id", user.getId(),
                    "username", user.getUsername(),
                    "email", user.getEmail(),
                    "message", "Login successful!"));
        }

        return ResponseEntity.badRequest()
                .body(Map.of("message", "Incorrect password."));
    }
}
