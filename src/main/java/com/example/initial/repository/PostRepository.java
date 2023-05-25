package com.example.initial.repository;

import com.example.initial.entity.Post;
import com.example.initial.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

  Post findByAuthor(String author);

  List<Post> findAllByUserId(Long userId);

  Long countByUser(User user);

  List<Post> findAllByAuthor(String author);
}
