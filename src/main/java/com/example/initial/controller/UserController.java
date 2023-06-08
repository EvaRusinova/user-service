package com.example.initial.controller;

import com.example.initial.entity.User;
import com.example.initial.interceptor.LoginCounterInterceptor;
import com.example.initial.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;
  private final LoginCounterInterceptor loginCounterInterceptor;

  @PostMapping("/register")
  public User registerUser(@RequestBody User user) {
    return userService.registerUser(user);
  }

  @GetMapping("/{id}")
  public User findById(@PathVariable(name = "id") Long id) {
    return userService.findById(id);
  }

  @GetMapping("/{userName}")
  public User findByUserName(@PathVariable(name = "userName") String userName) {
    return userService.findByUserName(userName);
  }

  @GetMapping("/getAll")
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }

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
