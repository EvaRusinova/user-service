package com.example.initial.service.impl;

import com.example.initial.entity.Comment;
import com.example.initial.repository.CommentRepository;
import com.example.initial.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public Comment findById(Long id) {
       return commentRepository.findById(id).orElseThrow();
    }

    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment findByAuthor(String author) {
        return commentRepository.findByAuthor(author);
    }

    public List<Comment> findAllComments() {
        return commentRepository.findAll();
    }
}
