package com.example.initial.controller;

import com.example.initial.entity.User;
import com.example.initial.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

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
}
