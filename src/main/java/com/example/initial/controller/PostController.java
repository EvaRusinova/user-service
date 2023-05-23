package com.example.initial.controller;

import com.example.initial.entity.Post;
import com.example.initial.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    @GetMapping("/{id}")
    public Post findById(@PathVariable(name = "id") Long id) {
        return postService.findById(id);
    }
    @GetMapping("/findByAuthor")
    public Post findByAuthor(@RequestParam(name = "author") String author) {
        return postService.findByAuthor(author);
    }
    @GetMapping("/getLikes")
    public Long countByPostId(@RequestParam(name = "postId") Long postId) {
        return postService.getLikesForPost(postId);
    }

}


