package com.example.initial;

import com.example.initial.entity.Comment;
import com.example.initial.entity.Post;
import com.example.initial.entity.User;
import com.example.initial.service.CommentService;
import com.example.initial.service.PostService;
import com.example.initial.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RequiredArgsConstructor
@SpringBootApplication
public class ApplicationRunner implements CommandLineRunner {

  private final UserService userService;
  private final CommentService commentService;
  private final PostService postService;

  public static void main(String[] args) {
    SpringApplication.run(ApplicationRunner.class, args);
  }

  @Override
  public void run(String... args) {
    //        bidirectionalMappingTest();

    commentService.save(Comment.builder().body("Az sym kote").author("Lolio").build());
    commentService.save(Comment.builder().body("Az sym malka mishka").author("Aleks").build());
    commentService.save(Comment.builder().body("Az sym tup").author("Toni sosa").build());

    postService.save(Post.builder().body("Photo with dog").author("Eva").likes(8888L).build());
    postService.save(Post.builder().body("Selfie photo").author("Toni").likes(5555L).build());
  }

  private void bidirectionalMappingTest() {
    User userOne = new User();
    userOne.setName("Eva Rusinova");
    userService.save(userOne);

    User userTwo = new User();
    userOne.setName("Alex Ivanov");
    userService.save(userTwo);

    Post postOne = new Post();
    postOne.setBody("I want to thank you all for making my birthday so special!");
    postOne.setUser(userOne);
    userOne.getPosts().add(postOne);

    Comment commentOne = new Comment();
    commentOne.setBody("Thank you, Alex, it was a wonderful day.");
    commentOne.setPost(postOne);
    commentOne.setUser(userOne);
    postOne.getComments().add(commentOne);

    Comment commentTwo = new Comment();
    commentTwo.setBody("You are special to all of us, Eva, we want you to be happy!");
    commentTwo.setPost(postOne);
    commentTwo.setUser(userTwo);
    postOne.getComments().add(commentTwo);
  }
}
