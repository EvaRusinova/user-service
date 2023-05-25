package com.example.initial.repository;

import com.example.initial.entity.Comment;
import com.example.initial.entity.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
  Comment findByAuthor(String author);

  List<Comment> findAllByPost(Post post);
}
