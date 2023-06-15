package com.example.initial.dto;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
@SuppressWarnings("unused")
public class UserLoginDto {
  private String userName;
  private String password;
}
