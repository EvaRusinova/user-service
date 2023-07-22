package com.example.initial.repository;

import com.example.initial.entity.User;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  @Query("SELECT COUNT(u) FROM User u WHERE u.createdAt >= :start")
  long countByCreationDateAfter(@Param("start") LocalDateTime start);

  User findByUserName(String name);

  User findByEmail(String email);
}
