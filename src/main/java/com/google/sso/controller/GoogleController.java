package com.google.sso.controller;

import com.google.sso.Service.GoogleVerificationService;
import com.google.sso.dto.UserIdTokenRequestDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/google")
public class GoogleController {

    private final GoogleVerificationService googleSSOService;

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping(value = "/token/verify", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> verifyIdToken(@RequestBody UserIdTokenRequestDTO idToken) throws GeneralSecurityException, IOException {
        log.info("verifyIdToken(): idToken: {}",idToken);
        return ResponseEntity.ok(googleSSOService.verify(idToken));
    }


}
