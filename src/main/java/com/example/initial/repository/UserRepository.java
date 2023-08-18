package com.example.initial.repository;

import com.example.initial.entity.User;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  long countByCreatedAtAfter(LocalDateTime start);

  User findByUsername(String username);

  User findByEmail(String email);
}
