package com.example.initial;

import com.example.initial.entity.Comment;
import com.example.initial.entity.Post;
import com.example.initial.entity.User;
import com.example.initial.service.UserService;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RequiredArgsConstructor
@SpringBootApplication
public class ApplicationRunner implements CommandLineRunner {

  private final UserService userService;

  public static void main(String[] args) {
    SpringApplication.run(ApplicationRunner.class, args);
  }

  @Override
  public void run(String... args) {
    List<User> testUsers =
        Arrays.asList(
            User.builder().name("John Doe").userName("john_doe").password("john_doe").build(),
            User.builder()
                .name("Jane Smith")
                .userName("jane_user")
                .password("JANESMITH93")
                .build());

    testUsers.forEach(
        user -> {
          List<Post> userPosts =
              IntStream.range(0, 2)
                  .mapToObj(
                      i ->
                          Post.builder()
                              .author(user.getName())
                              .title("Test Post " + (i + 1))
                              .body("Body of Test Post " + (i + 1))
                              .user(user)
                              .likes((long) ThreadLocalRandom.current().nextInt(101))
                              .build())
                  .peek(
                      post -> {
                        List<Comment> postComments =
                            IntStream.range(0, 2)
                                .mapToObj(
                                    j ->
                                        Comment.builder()
                                            .author(user.getName())
                                            .body("Test Comment " + (j + 1))
                                            .post(post)
                                            .build())
                                .collect(Collectors.toList());

                        post.setComments(postComments);
                      })
                  .collect(Collectors.toList());

          user.setPosts(userPosts);
        });

    userService.saveAll(testUsers);
  }
}
