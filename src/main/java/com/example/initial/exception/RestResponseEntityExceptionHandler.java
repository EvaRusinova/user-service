package com.example.initial.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class})
  protected ResponseEntity<Object> handleIllegalEx(RuntimeException ex, WebRequest request) {
    String bodyOfResponse = "Invalid request.";
    return handleExceptionInternal(
        ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  protected ResponseEntity<Object> handleDataIntegrityViolationEx(
      DataIntegrityViolationException ex, WebRequest request) {
    String bodyOfResponse = "Duplicate user name.";
    return handleExceptionInternal(
        ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
  }

  // TODO: add exception handler for wrong format of the file that we try to upload

}
