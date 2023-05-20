package com.example.evafirstproject.controller;


import com.example.evafirstproject.entity.Comment;
import com.example.evafirstproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
