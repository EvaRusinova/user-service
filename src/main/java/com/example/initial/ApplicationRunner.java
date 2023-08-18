package com.example.initial;

import com.example.initial.entity.Comment;
import com.example.initial.entity.Post;
import com.example.initial.entity.User;
import com.example.initial.enums.Role;
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
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableScheduling
@RequiredArgsConstructor
@SpringBootApplication
public class ApplicationRunner implements CommandLineRunner {

  private final UserService userService;
  private final PasswordEncoder passwordEncoder;

  public static void main(String[] args) {
    SpringApplication.run(ApplicationRunner.class, args);
  }

  @Override
  public void run(String... args) {
    List<User> testUsers =
        Arrays.asList(
            User.builder()
                .fullName("John Doe")
                .username("john_doe")
                .password(passwordEncoder.encode("eva213"))
                .roles(Role.valueOf("USER"))
                .creditCard("4242424242424242")
                .age(25)
                .build(),
            User.builder()
                .fullName("admin")
                .username("admin")
                .password(passwordEncoder.encode("adminADMIN"))
                .creditCard("4242424242424242")
                .roles(Role.valueOf("ADMIN"))
                .age(25)
                .build());

    testUsers.forEach(
        user -> {
          List<Post> userPosts =
              IntStream.range(0, 2)
                  .mapToObj(
                      i ->
                          Post.builder()
                              .author(user.getFullName())
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
                                            .author(user.getFullName())
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
