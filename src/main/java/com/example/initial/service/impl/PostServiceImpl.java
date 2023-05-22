package com.example.initial.service.impl;

import com.example.initial.entity.Post;
import com.example.initial.repository.PostRepository;
import com.example.initial.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public Post findById(Long id) {
        return postRepository.findById(id).orElseThrow();
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public Post findByAuthor(String author) {
        return postRepository.findByAuthor(author);
    }

    public List<Post> findAllLikes() {
        return postRepository.findAll();
    }

}
