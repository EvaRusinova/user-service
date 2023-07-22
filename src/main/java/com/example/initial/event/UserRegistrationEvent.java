package com.example.initial.event;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserRegistrationEvent implements Serializable {
  private static final long serialVersionUID = 898989;
  private String userName;
  private String email;
}