package com.example.initial.controller;

import com.example.initial.dto.UserRegistrationDto;
import com.example.initial.interceptor.LoginCounterInterceptor;
import com.example.initial.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "Users")
@RestController
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;
  private final LoginCounterInterceptor loginCounterInterceptor;

  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@RequestBody UserRegistrationDto userRegistrationDto) {
    userService.registerUser(userRegistrationDto);
    return ResponseEntity.ok("Registration successful");
  }

  @GetMapping("/login/count")
  public int getLoginCount() {
    return loginCounterInterceptor.getLoginCount();
  }
}
