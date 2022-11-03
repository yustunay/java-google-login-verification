package com.google.sso.service;

import com.google.sso.dto.UserIdTokenRequestDTO;
import com.google.sso.dto.UserIdTokenResponseDTO;
import com.google.sso.proxy.GoogleAuthProxy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class GoogleVerificationService {

    private final GoogleAuthProxy authProxy;

    public UserIdTokenResponseDTO verify(UserIdTokenRequestDTO idToken) {
        return authProxy.verify(idToken);
    }
}
