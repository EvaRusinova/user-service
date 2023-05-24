package com.example.initial.controller;

import com.example.initial.entity.Comment;
import com.example.initial.service.CommentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{id}")
    public Comment findById(@PathVariable(name = "id") Long id) {
        return commentService.findById(id);
    }

    @GetMapping("/findByAuthor")
    public Comment findByAuthor(@RequestParam(name = "author") String author) {
        return commentService.findByAuthor(author);
    }

    @GetMapping("/getAll")
    public List<Comment> getAllComments() {
        return commentService.findAllComments();
    }
}
