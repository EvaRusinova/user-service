package com.example.evafirstproject.service.impl;

import com.example.evafirstproject.entity.Comment;
import com.example.evafirstproject.repository.CommentRepository;
import com.example.evafirstproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    @Override
    public Comment findByAuthor(String author) {
        return commentRepository.findByAuthor(author);
    }
}
