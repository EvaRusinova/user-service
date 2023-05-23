package com.example.initial;

import com.example.initial.entity.Comment;
import com.example.initial.entity.Post;
import com.example.initial.service.CommentService;
import com.example.initial.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RequiredArgsConstructor
@SpringBootApplication
public class ApplicationRunner implements CommandLineRunner {

    private final CommentService commentService;
    private final PostService postService;

    public static void main(String[] args) {
        SpringApplication.run(ApplicationRunner.class, args);
    }
    @Override
    public void run(String... args) {
        commentService.save(Comment.builder().body("Az sym kote").author("Lolio").build());
        commentService.save(Comment.builder().body("Az sym malka mishka").author("Aleks").build());
        commentService.save(Comment.builder().body("Az sym tup").author("Toni sosa").build());


        postService.save(Post.builder().body("Photo with dog").author("Eva").postId(8L).build());
        postService.save(Post.builder().body("Selfie photo").author("Toni").postId(10L).build());
    }
}
