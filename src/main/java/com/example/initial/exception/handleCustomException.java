package com.example.initial.exception;

import lombok.Getter;

@Getter
public class handleCustomException extends RuntimeException {
  private final String errorCode;

  public handleCustomException(String message, String errorCode) {
    super(message);
    this.errorCode = errorCode;
  }
}
