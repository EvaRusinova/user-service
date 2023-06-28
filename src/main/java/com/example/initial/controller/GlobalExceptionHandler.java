package com.example.initial.controller;

import com.example.initial.error.ErrorResponse;
import com.example.initial.exception.*;
import java.time.LocalDateTime;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(CustomAppException.class)
  public ResponseEntity<ErrorResponse> handleMyAppException(CustomAppException ex) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
    errorResponse.setMessage(ex.getMessage());
    errorResponse.setTimestamp(LocalDateTime.now());

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(DatabaseException.class)
  public ResponseEntity<ErrorResponse> handleDatabaseException(DatabaseException ex) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    errorResponse.setMessage("Database error: " + ex.getMessage());
    errorResponse.setTimestamp(LocalDateTime.now());

    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(AuthorizationException.class)
  public ResponseEntity<ErrorResponse> handleAuthorizationException(AuthorizationException ex) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
    errorResponse.setMessage("Authorization error: " + ex.getMessage());
    errorResponse.setTimestamp(LocalDateTime.now());

    return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(ServerErrorException.class)
  public ResponseEntity<ErrorResponse> handleServerErrorException(ServerErrorException ex) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
    errorResponse.setMessage("The server is currently updating. Please try again later.");
    errorResponse.setTimestamp(LocalDateTime.now());

    return new ResponseEntity<>(errorResponse, HttpStatus.SERVICE_UNAVAILABLE);
  }

  @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class})
  protected ResponseEntity<Object> handleIllegalEx(RuntimeException ex, WebRequest request) {
    String bodyOfResponse = "Invalid request.";
    return ResponseEntity.status(HttpStatus.CONFLICT).body(bodyOfResponse);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  protected ResponseEntity<Object> handleDataIntegrityViolationEx(
      DataIntegrityViolationException ex, WebRequest request) {
    String bodyOfResponse = "Duplicate user name.";
    return ResponseEntity.status(HttpStatus.CONFLICT).body(bodyOfResponse);
  }

  @ExceptionHandler(InvalidCredentialsException.class)
  public ResponseEntity<ErrorResponse> handleInvalidCredentialsException(
      InvalidCredentialsException ex) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
    errorResponse.setMessage(ex.getMessage());
    errorResponse.setTimestamp(LocalDateTime.now());

    return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleException(Exception ex) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    errorResponse.setMessage("An unexpected error occurred");
    errorResponse.setTimestamp(LocalDateTime.now());

    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
