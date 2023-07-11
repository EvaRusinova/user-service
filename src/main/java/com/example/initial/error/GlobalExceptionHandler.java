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
    return buildErrorResponse(ex, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(DatabaseException.class)
  public ResponseEntity<ErrorResponse> handleDatabaseException(DatabaseException ex) {
    return buildErrorResponse(ex, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(AuthorizationException.class)
  public ResponseEntity<ErrorResponse> handleAuthorizationException(AuthorizationException ex) {
    return buildErrorResponse(ex, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(ServerErrorException.class)
  public ResponseEntity<ErrorResponse> handleServerErrorException(ServerErrorException ex) {
    return buildErrorResponse(ex, HttpStatus.SERVICE_UNAVAILABLE);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorResponse> handleIllegalEx(IllegalArgumentException ex) {
    return buildErrorResponse(ex, HttpStatus.SERVICE_UNAVAILABLE);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ErrorResponse> handleDataIntegrityViolationEx(
      DataIntegrityViolationException ex) {
    return buildErrorResponse(ex, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(InvalidCredentialsException.class)
  public ResponseEntity<ErrorResponse> handleInvalidCredentialsException(
      InvalidCredentialsException ex) {
    return buildErrorResponse(ex, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleException(Exception ex) {
    return buildErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  private ResponseEntity<ErrorResponse> buildErrorResponse(Exception ex, HttpStatus status) {
    ErrorResponse errorResponse =
        ErrorResponse.builder()
            .status(status.value())
            .message(ex.getMessage())
            .errorCode(
                ex instanceof CustomException
                    ? ((CustomException) ex).getErrorCode()
                    : "DEFAULT_ERROR_CODE")
            .build();

    return ResponseEntity.status(status).body(errorResponse);
  }
}
