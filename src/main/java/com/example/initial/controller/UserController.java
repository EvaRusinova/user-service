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

  @PostMapping("/save")
  public User saveUser(@RequestBody User user) {
    return userService.saveUser(user);
  }

  @GetMapping("/{id}")
  public User findById(@PathVariable(name = "id") Long id) {
    return userService.findById(id);
  }

  @GetMapping("/getAll")
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }
}
