package com.cs4135.Backend.exception;

import javax.naming.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleException(Exception ex) {
    ex.printStackTrace(); // will print full stack trace to console
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(ex.getClass().getName() + ": " + ex.getMessage());
  }

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<String> handleBadCredentials(BadCredentialsException ex) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        .body("Invalid username or password");
  }

  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<String> handleAuthException(AuthenticationException ex) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        .body("Authentication failed");
  }
}
