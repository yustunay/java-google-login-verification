package com.google.sso.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class UserIdTokenResponseDTO {
    private String userId;
    private String email;
    private Boolean emailVerified;
    private String name;
    private String pictureUrl;
    private String locale;
    private String familyName;
    private String givenName;
}
