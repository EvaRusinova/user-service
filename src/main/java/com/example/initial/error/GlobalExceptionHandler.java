package com.example.initial.error;

import com.example.initial.exception.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(CustomException.class)
  public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {
    return buildResponseEntity(HttpStatus.BAD_REQUEST, ex);
  }

  @ExceptionHandler(DatabaseException.class)
  public ResponseEntity<ErrorResponse> handleDatabaseException(DatabaseException ex) {
    return buildResponseEntity(HttpStatus.CONFLICT, ex);
  }

  @ExceptionHandler(AuthorizationException.class)
  public ResponseEntity<ErrorResponse> handleAuthorizationException(AuthorizationException ex) {
    return buildResponseEntity(HttpStatus.UNAUTHORIZED, ex);
  }

  @ExceptionHandler(ServerErrorException.class)
  public ResponseEntity<ErrorResponse> handleServerErrorException(ServerErrorException ex) {
    return buildResponseEntity(HttpStatus.SERVICE_UNAVAILABLE, ex);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorResponse> handleIllegalEx(IllegalArgumentException ex) {
    return buildResponseEntity(HttpStatus.SERVICE_UNAVAILABLE, ex);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ErrorResponse> handleDataIntegrityViolationEx(
      DataIntegrityViolationException ex) {
    return buildResponseEntity(HttpStatus.CONFLICT, ex);
  }

  @ExceptionHandler(InvalidCredentialsException.class)
  public ResponseEntity<ErrorResponse> handleInvalidCredentialsException(
      InvalidCredentialsException ex) {
    return buildResponseEntity(HttpStatus.UNAUTHORIZED, ex);
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
    return buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, ex);
  }

  private <T extends RuntimeException> ResponseEntity<ErrorResponse> buildResponseEntity(
      HttpStatus status, T ex) {
    String errorCode = null;
    if (ex instanceof CustomException) {
      errorCode = ((CustomException) ex).getErrorCode();
    }

    ErrorResponse errorResponse =
        ErrorResponse.builder()
            .status(status.value())
            .message(ex.getMessage())
            .errorCode(errorCode)
            .build();
    return ResponseEntity.status(status).body(errorResponse);
  }
}
