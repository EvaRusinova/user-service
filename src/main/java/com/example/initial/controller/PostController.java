package com.example.initial.controller;

import com.example.initial.entity.Post;
import com.example.initial.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    @GetMapping("/{id}")
    public Post findById(@PathVariable(name = "id") Long id) {
        return postService.findById(id);
    }

}
