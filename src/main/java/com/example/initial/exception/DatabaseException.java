package com.example.initial.exception;

public class DatabaseException extends RuntimeException {
  private final String errorCode;

  public DatabaseException(String message, String errorCode) {
    super(message);
    this.errorCode = errorCode;
  }

  public String getErrorCode() {
    return errorCode;
  }
}
