package com.example.initial.controller;

import com.example.initial.dto.UserLoginDto;
import com.example.initial.entity.User;
import com.example.initial.interceptor.LoginCounterInterceptor;
import com.example.initial.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Users")
@RepositoryRestController
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;
  private final LoginCounterInterceptor loginCounterInterceptor;

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody UserLoginDto userLoginDto) {
    User existingUser = userService.findByUserName(userLoginDto.getUserName());

    if (existingUser == null || !existingUser.getPassword().equals(userLoginDto.getPassword())) {
      // Invalid username/password
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
    }

    // Successful login
    return ResponseEntity.ok(
        "Login successful, logged in "
            + loginCounterInterceptor.getLoginCount()
            + " times in total");
  }

  @GetMapping("/login/count")
  public int getLoginCount() {
    return loginCounterInterceptor.getLoginCount();
  }
}
