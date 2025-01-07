package com.swipebyte.project.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.*;
import com.swipebyte.project.entity.*;

@RestController
@RequestMapping("/location")
public class LocationAutoController {

    @Value("${places.api.key}")
    private String key;

    @GetMapping("/autocomplete")
    public ResponseEntity<?> getAutoComplete(@RequestParam String input) {

        try {

            String url = "https://maps.googleapis.com/maps/api/place/autocomplete/json?input=" + input
                    + "&types=locality&components=country:us&key=" + key;

            RestTemplate restTemplate = new RestTemplate();

            String response = restTemplate.getForObject(url, String.class);

            return ResponseEntity.ok().body(response);

        }

        catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error finding autocomplete locations.");

        }

    }
}
