package com.example.initial.exception;

import lombok.Getter;

@Getter
public class ServerErrorException extends RuntimeException {
  private final String errorCode;

  public ServerErrorException(String message, String errorCode) {
    super(message);
    this.errorCode = errorCode;
  }
}
