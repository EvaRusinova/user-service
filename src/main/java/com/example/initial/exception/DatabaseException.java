package com.example.initial.exception;

import lombok.Getter;

@Getter
public class DatabaseException extends RuntimeException {
  private final String errorCode;

  public DatabaseException(String message, String errorCode) {
    super(message);
    this.errorCode = errorCode;
  }
}
