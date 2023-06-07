package com.example.initial;

import com.example.initial.entity.Post;
import com.example.initial.entity.User;
import com.example.initial.service.CommentService;
import com.example.initial.service.PostService;
import com.example.initial.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

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

    List<User> testUsers =
        Arrays.asList(
            User.builder()
                .name("John Doe")
                .userName("john_doe")
                .password("34johndoe")
                .posts(
                    Arrays.asList(
                        Post.builder()
                            .author("John Doe")
                            .title("Post 1")
                            .body("Body of post 1")
                            .build(),
                        Post.builder()
                            .author("John Doe")
                            .title("Post 2")
                            .body("Body of post 2")
                            .build()))
                .build(),
            User.builder()
                .name("Jane Smith")
                .userName("jane_user")
                .password("JANESMITH93")
                .posts(
                    Arrays.asList(
                        Post.builder()
                            .author("Jane Smith")
                            .title("Post 3")
                            .body("Body of post 3")
                            .build(),
                        Post.builder()
                            .author("Jane Smith")
                            .title("Post 4")
                            .body("Body of post 4")
                            .build()))
                .build());

    // Save the test users
    userService.saveAll(testUsers);

    //    commentService.save(Comment.builder().body("Az sym kote").author("Lolio").build());
    //    commentService.save(Comment.builder().body("Az sym malka
    // mishka").author("Aleks").build());
    //    commentService.save(Comment.builder().body("Az sym tup").author("Toni sosa").build());

    //    postService.save(Post.builder().body("Photo with
    // dog").author("Eva").likes(8888L).build());
    //    postService.save(Post.builder().body("Selfie photo").author("Toni").likes(5555L).build());
    //

  }
}
