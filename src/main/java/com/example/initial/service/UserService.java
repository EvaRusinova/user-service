package com.example.initial.service;

import com.example.initial.entity.User;
import java.util.List;

public interface UserService {
  // TODO: return User, not optional
  User findById(Long id);

  User save(User user);

  List<User> getAllUsers();
}
