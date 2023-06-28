package com.example.initial.exception;

public class ServerErrorException extends RuntimeException {
  public ServerErrorException(String message) {
    super(message);
  }
}
