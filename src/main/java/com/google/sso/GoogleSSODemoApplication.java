package com.google.sso;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class GoogleSSODemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoogleSSODemoApplication.class, args);
        log.info("Google SSO Verification Service has just started...");
    }


}
