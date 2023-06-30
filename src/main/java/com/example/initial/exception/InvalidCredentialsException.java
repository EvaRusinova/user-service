package com.example.initial.exception;

import lombok.Getter;

@Getter
public class InvalidCredentialsException extends RuntimeException {
  private final String errorCode;

  public InvalidCredentialsException(String message, String errorCode) {
    super(message);
    this.errorCode = errorCode;
  }
}
