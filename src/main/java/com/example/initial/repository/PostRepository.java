package com.example.initial.repository;

import com.example.initial.entity.Post;
import com.example.initial.entity.User;
import java.util.List;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
  @Cacheable("postByAuthor")
  Post findByAuthor(String author);

  @Cacheable("allPostsByUserId")
  List<Post> findAllByUserId(Long userId);

  Long countByUser(User user);

  @Cacheable("allPostsByAuthor")
  List<Post> findAllByAuthor(String author);
}
