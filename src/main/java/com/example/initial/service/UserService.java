package com.example.initial.service;

import com.example.initial.entity.User;
import java.util.List;

public interface UserService {

  User findById(Long id);

  User save(User user);

  List<User> saveAll(List<User> users);

  List<User> getAllUsers();

  void deleteById(Long id);
}
