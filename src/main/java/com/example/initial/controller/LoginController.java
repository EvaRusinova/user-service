package com.example.initial.controller;

import com.example.initial.entity.User;
import com.example.initial.interceptor.LoginCounterInterceptor;
import com.example.initial.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class LoginController {

  private final UserRepository userService;
  private final LoginCounterInterceptor loginCounterInterceptor;

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody User user) {
    User existingUser = userService.findByUserName(user.getUserName());

    if (existingUser == null || !existingUser.getPassword().equals(user.getPassword())) {
      // Invalid username/password
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
    }

    // Successful login
    return ResponseEntity.ok("Login successful");
  }

  @GetMapping("/login/count")
  public int getLoginCount() {
    return loginCounterInterceptor.getLoginCount();
  }
}
