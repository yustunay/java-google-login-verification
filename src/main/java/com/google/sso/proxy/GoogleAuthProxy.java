package com.google.sso.proxy;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.sso.dto.UserIdTokenResponseDTO;
import com.google.sso.exception.GoogleSSODemoException;
import com.google.sso.dto.UserIdTokenRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Slf4j
@Service
public class GoogleAuthProxy {

    private GoogleIdTokenVerifier verifier;

    @Value("${google.app.client.id}")
    private String clientId;

    @PostConstruct
    public void init() throws GeneralSecurityException, IOException {
       log.info("Creating verifier...");
       verifier = new GoogleIdTokenVerifier.Builder(GoogleNetHttpTransport.newTrustedTransport(), GsonFactory.getDefaultInstance())
                // Specify the CLIENT_ID of the app that accesses the backend:
                .setAudience(Collections.singletonList(clientId))
                // Or, if multiple clients access the backend:
                //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
                .build();
    }

    public UserIdTokenResponseDTO verify(UserIdTokenRequestDTO idTokenString) {
        try{
            GoogleIdToken idToken = verifier.verify(idTokenString.getToken());

            if (idToken != null) {
                GoogleIdToken.Payload payload = idToken.getPayload();

                UserIdTokenResponseDTO idTokenResponse =  UserIdTokenResponseDTO.builder()
                        .userId(payload.getSubject())
                        .email(payload.getEmail())
                        .emailVerified(Boolean.valueOf(payload.getEmailVerified()))
                        .name((String) payload.get("name"))
                        .pictureUrl((String) payload.get("picture"))
                        .locale((String) payload.get("locale"))
                        .familyName((String) payload.get("family_name"))
                        .givenName((String) payload.get("given_name"))
                        .build();

                log.info("idTokenResponse: {}",idTokenResponse);
                return idTokenResponse;

            } else {
                log.error("Invalid ID token");
                throw new GoogleSSODemoException("Invalid Token!", HttpStatus.BAD_REQUEST);
            }

        } catch (GeneralSecurityException | IOException e) {
            log.error("Invalid ID token. Detail: {}",e);
            throw new GoogleSSODemoException("Invalid Token Exception! "+e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (GoogleSSODemoException e) {
            throw e;
        } catch (Exception e){
            log.error("Generic exception occurred: {}",e);
            throw new GoogleSSODemoException("Generic exception occurred! "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }







}
