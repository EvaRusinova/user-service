package com.example.initial.repository;

import com.example.initial.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  User findByUserName(String name);

  User findByEmail(String email);

  Boolean existsByEmail(String email);

  Boolean existsByUserName(String userName);
}
