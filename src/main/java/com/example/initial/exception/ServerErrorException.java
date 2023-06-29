package com.example.initial.exception;

public class ServerErrorException extends RuntimeException {
  private final String errorCode;

  public ServerErrorException(String message, String errorCode) {
    super(message);
    this.errorCode = errorCode;
  }

  public String getErrorCode() {
    return errorCode;
  }
}
