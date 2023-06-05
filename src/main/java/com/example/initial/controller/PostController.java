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

  @PostMapping("/save")
  public Post savePost(@RequestBody Post post) {
    return postService.savePost(post);
  }

  @GetMapping("/getLikes")
  public Long getLikesForPost(@RequestParam(name = "postId") Long id) {
    return postService.getLikesForPost(id);
  }
}
