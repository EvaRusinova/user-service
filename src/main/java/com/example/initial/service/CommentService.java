package com.example.initial.service;

import com.example.initial.entity.Comment;
import java.util.List;

public interface CommentService {

  Comment findById(Long id);

  Comment findByAuthor(String author);

  List<Comment> findAllComments();

  Comment saveComment(Comment comment);
}
