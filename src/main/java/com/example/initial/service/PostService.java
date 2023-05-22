package com.example.initial.service;

import com.example.initial.entity.Post;

import java.util.List;

public interface PostService {
    Post findById(Long id);

    Post findByAuthor(String author);

    List<Post> findAllLikes();
    Post save(Post post);
}
