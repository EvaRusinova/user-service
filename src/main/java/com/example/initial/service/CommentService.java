package com.example.initial.service;

import com.example.initial.entity.Comment;

import java.util.List;

public interface CommentService {

    Comment findById(Long id);

    Comment save(Comment comment);

    Comment findByAuthor(String author);

    List<Comment> findAllComments();
}
