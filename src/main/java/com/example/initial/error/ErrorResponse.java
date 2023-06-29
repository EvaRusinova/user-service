package com.example.initial.error;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ErrorResponse {
  private int status;
  private String message;
  private String errorCode;
}
