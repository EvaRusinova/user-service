package com.example.initial.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SuppressWarnings("unused")
public class UserRegistrationDto {
  private String fullName;
  private Integer age;
  private String email;
  private String username;
  private String password;
}
