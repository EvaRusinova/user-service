package com.example.initial.exception;

import lombok.Getter;

@Getter
public class AuthorizationException extends RuntimeException {
  private final String errorCode;

  public AuthorizationException(String message, String errorCode) {
    super(message);
    this.errorCode = errorCode;
  }
}
