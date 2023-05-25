package com.example.initial.service;

import com.example.initial.entity.Post;

public interface PostService {
  Post findById(Long id);

  Post findByAuthor(String author);

  Post save(Post post);

  Long getLikesForPost(Long id);
}
