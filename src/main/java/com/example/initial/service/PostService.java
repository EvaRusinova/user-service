package com.example.initial.service;

import com.example.initial.entity.Post;
import org.springframework.web.bind.annotation.RequestParam;

public interface PostService {
    Post findById(Long id);

    Post findByAuthor(String author);

    Long getLikesForPost(@RequestParam(name = "postId") Long postId);
    Post save(Post post);

}
