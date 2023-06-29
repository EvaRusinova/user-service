package com.example.initial.controller;

import com.example.initial.dto.UserLoginDto;
import com.example.initial.entity.User;
import com.example.initial.exception.InvalidCredentialsException;
import com.example.initial.interceptor.LoginCounterInterceptor;
import com.example.initial.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "Users")
@RestController
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;
  private final LoginCounterInterceptor loginCounterInterceptor;

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody UserLoginDto userLoginDto) {
    User existingUser = userService.findByUserName(userLoginDto.getUserName());

    if (existingUser == null || !existingUser.getPassword().equals(userLoginDto.getPassword())) {
      log.warn("Failed login attempt for username: {}", userLoginDto.getUserName());
      // Invalid username/password
      throw new InvalidCredentialsException("Invalid username or password", "INVALID_CREDENTIALS");
    }

    // Successful login
    log.info("Successful login for username: {}", userLoginDto.getUserName());
    return ResponseEntity.ok("Login successful");
  }

  @GetMapping("/login/count")
  public int getLoginCount() {
    return loginCounterInterceptor.getLoginCount();
  }
}
