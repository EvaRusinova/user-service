package com.example.evafirstproject;

import com.example.evafirstproject.entity.Comment;
import com.example.evafirstproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RequiredArgsConstructor
@SpringBootApplication
public class ApplicationRunner implements CommandLineRunner {

    private final CommentService commentService;

    public static void main(String[] args) {
        SpringApplication.run(ApplicationRunner.class, args);
    }

    @Override
    public void run(String... args) {
        commentService.save(Comment.builder().body("Az sym kote").author("Lolio").build());
        commentService.save(Comment.builder().body("Az sym malka mishka").author("Aleks").build());
        commentService.save(Comment.builder().body("Az sym tup").author("Toni sosa").build());
    }
}
