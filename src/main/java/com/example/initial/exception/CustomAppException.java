package com.example.initial.exception;

public class CustomAppException extends RuntimeException {
  public CustomAppException(String message) {
    super(message);
  }
}
