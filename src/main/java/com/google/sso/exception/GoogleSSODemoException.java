package com.google.sso.exception;

import org.springframework.http.HttpStatus;

public class GoogleSSODemoException extends RuntimeException {
    private HttpStatus status;

    public GoogleSSODemoException(String message){
        super(message);
    }

    public GoogleSSODemoException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
