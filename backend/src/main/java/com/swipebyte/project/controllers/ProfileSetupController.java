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

    @PostMapping("/profile")
    public ResponseEntity<?> editProfile(@RequestBody ProfileDto profile) {

        UserEntity user = userRepo.findById(profile.getUserId()).get();

        UserProfile currProfile = user.getProfile();

        if (currProfile == null) {

            return ResponseEntity.badRequest().body("Profile not found.");
        }
        String currentBio = currProfile.getBio();
        List<String> currentCuisines = currProfile.getFavoritecuisines();

        List<String> newCuisines = profile.getFavoriteCuisines();
        String newBio = profile.getBio();
        if (!newBio.equals(currentBio)) {

            currProfile.setBio(newBio);
        }

        if (!newCuisines.equals(currentCuisines)) {
            currProfile.setFavoritecuisines(newCuisines);
        }

        profileRepo.save(currProfile);

        return ResponseEntity.ok("Profile changed!");
    }

}
