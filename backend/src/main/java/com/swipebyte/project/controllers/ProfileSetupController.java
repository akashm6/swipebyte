package com.swipebyte.project.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.*;

import org.springframework.http.ResponseEntity;
import java.util.*;
import com.swipebyte.project.dto.*;
import com.swipebyte.project.entity.*;
import com.swipebyte.project.repository.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class ProfileSetupController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ProfileRepository profileRepo;

    @PostMapping("/setup_profile")
    public ResponseEntity<?> editProfile(@RequestBody ProfileDto profile) {

        UserProfile currentProfile = profileRepo.findById(profile.getId()).orElse(null);

        if (currentProfile == null) {

            return ResponseEntity.badRequest().body("Profile not found.");
        }
        String currentBio = currentProfile.getBio();

        String newBio = profile.getBio();
        if (!newBio.equals(currentBio)) {

            currentProfile.setBio(newBio);
        }

        profileRepo.save(currentProfile);

        return ResponseEntity.ok(new ProfileDto(profile.getId(), profile.getBio(), profile.getFavoriteCuisines()));
    }

}
