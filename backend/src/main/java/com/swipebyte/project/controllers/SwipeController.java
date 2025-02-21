package com.swipebyte.project.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
public class SwipeController {

    // User swipes left, we remove restaurant from redis cache, increment swipe
    // count, and hand to model
    @PostMapping("/swipeLeft")
    public ResponseEntity<?> swipeLeft() {

        return null;

    }

    @PostMapping("/swipeRight")
    // user swipes right, restaurant is saved in repo, removed from redis cache,
    // increment swipe count,
    // add to userprofile, and hand to model
    public ResponseEntity<?> swipeRight() {

        return null;

    }

}
