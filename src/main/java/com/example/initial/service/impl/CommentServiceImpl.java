package com.example.initial.service.impl;

import com.example.initial.entity.Comment;
import com.example.initial.repository.CommentRepository;
import com.example.initial.service.CommentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

  private final CommentRepository commentRepository;

  public Comment findById(Long id) {
    return commentRepository.findById(id).orElseThrow();
  }

  public Comment findByAuthor(String author) {
    return commentRepository.findByAuthor(author);
  }

  public List<Comment> findAllComments() {
    return commentRepository.findAll();
  }

  public Comment saveComment(Comment comment) {
    return commentRepository.save(comment);
  }
}
