package com.example.initial.error;

import com.example.initial.exception.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(handleCustomException.class)
  public ResponseEntity<ErrorResponse> handleMyAppException(handleCustomException ex) {
    ErrorResponse errorResponse =
        ErrorResponse.builder()
            .status(HttpStatus.BAD_REQUEST.value())
            .message(ex.getMessage())
            .errorCode(ex.getErrorCode())
            .build();

    return ResponseEntity.badRequest().body(errorResponse);
  }

  @ExceptionHandler(DatabaseException.class)
  public ResponseEntity<ErrorResponse> handleDatabaseException(DatabaseException ex) {
    ErrorResponse errorResponse =
        ErrorResponse.builder()
            .status(HttpStatus.CONFLICT.value())
            .message(ex.getMessage())
            .errorCode(ex.getErrorCode())
            .build();

    return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
  }

  @ExceptionHandler(AuthorizationException.class)
  public ResponseEntity<ErrorResponse> handleAuthorizationException(AuthorizationException ex) {
    ErrorResponse errorResponse =
        ErrorResponse.builder()
            .status(HttpStatus.UNAUTHORIZED.value())
            .message(ex.getMessage())
            .errorCode(ex.getErrorCode())
            .build();

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
  }

  @ExceptionHandler(ServerErrorException.class)
  public ResponseEntity<ErrorResponse> handleServerErrorException(ServerErrorException ex) {
    ErrorResponse errorResponse =
        ErrorResponse.builder()
            .status(HttpStatus.SERVICE_UNAVAILABLE.value())
            .message(ex.getMessage())
            .errorCode(ex.getErrorCode())
            .build();

    return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(errorResponse);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorResponse> handleIllegalEx(handleCustomException ex) {
    ErrorResponse errorResponse =
        ErrorResponse.builder()
            .status(HttpStatus.SERVICE_UNAVAILABLE.value())
            .message(ex.getMessage())
            .errorCode(ex.getErrorCode())
            .build();

    return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(errorResponse);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ErrorResponse> handleDataIntegrityViolationEx(handleCustomException ex) {
    ErrorResponse errorResponse =
        ErrorResponse.builder()
            .status(HttpStatus.CONFLICT.value())
            .message(ex.getMessage())
            .errorCode(ex.getErrorCode())
            .build();
    return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
  }

  @ExceptionHandler(InvalidCredentialsException.class)
  public ResponseEntity<ErrorResponse> handleInvalidCredentialsException(
      InvalidCredentialsException ex) {
    ErrorResponse errorResponse =
        ErrorResponse.builder()
            .status(HttpStatus.UNAUTHORIZED.value())
            .message(ex.getMessage())
            .errorCode(ex.getErrorCode())
            .build();

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleException(handleCustomException ex) {
    ErrorResponse errorResponse =
        ErrorResponse.builder()
            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .message(ex.getMessage())
            .errorCode(ex.getErrorCode())
            .build();

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
  }
}
