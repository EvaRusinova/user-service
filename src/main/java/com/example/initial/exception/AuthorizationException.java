package com.example.initial.exception;

public class AuthorizationException extends RuntimeException {
  private final String errorCode;

  public AuthorizationException(String message, String errorCode) {
    super(message);
    this.errorCode = errorCode;
  }

  public String getErrorCode() {
    return errorCode;
  }
}
