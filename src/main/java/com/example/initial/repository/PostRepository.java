package com.example.initial.repository;

import com.example.initial.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByAuthor(String author);

    Long getLikesForPostId(Long postId);

}
