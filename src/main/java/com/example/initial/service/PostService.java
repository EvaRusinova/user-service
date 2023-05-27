package com.example.initial.service;

import com.example.initial.entity.Post;

public interface PostService {
  Post findById(Long id);

  Post findByAuthor(String author);

  Long getLikesForPost(Long id);

  Post savePost(Post post);
}
