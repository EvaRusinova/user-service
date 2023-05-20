package com.example.evafirstproject.service;

import com.example.evafirstproject.entity.Comment;

public interface CommentService {

    Comment findById(Long id);

    Comment save(Comment comment);

    Comment findByAuthor(String author);
}
