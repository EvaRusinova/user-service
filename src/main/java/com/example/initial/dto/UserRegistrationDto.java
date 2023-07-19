package com.example.initial.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRegistrationDto {
  private String name;
  private Integer age;
  private String email;
  private String userName;
  private String password;
}
