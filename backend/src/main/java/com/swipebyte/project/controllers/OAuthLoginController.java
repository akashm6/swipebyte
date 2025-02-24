package com.swipebyte.project.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class OAuthLoginController {

    @PostMapping("/login/oauth2/code/github")
    public ResponseEntity<?> HandleGithubLogin(@AuthenticationPrincipal OAuth2User user) {

        return ResponseEntity.ok(user.getAttributes());

    }

}
