package com.bitestream.project.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.bitestream.project.dto.*;
import com.bitestream.project.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import com.bitestream.project.repository.UserRepository;

@RestController
public class RegisterController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationDto registerDto) {
        if (userRepo.existsByEmail(registerDto.getEmail())) {
            return ResponseEntity.badRequest().body("This email is already registered. Try again.");
        }

        UserEntity user = new UserEntity();
        // BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setUsername(registerDto.getUsername());
        user.setFirst_name(registerDto.getFirst_name());
        user.setLast_name(registerDto.getLast_name());
        String encodedPassword = encoder.encode(registerDto.getPassword());
        user.setPassword(encodedPassword);
        user.setLocation(registerDto.getLocation());
        user.setEmail(registerDto.getEmail());
        user.setNumcoins(0);
        user.setSwipedRightRestaurants(new HashSet<Restaurant>());
        user.setVisitedRestaurants(new HashSet<Restaurant>());
        user.setProfile(new UserProfile());

        userRepo.save(user);

        return ResponseEntity.ok("User Registered Successfully!");
    }

}
