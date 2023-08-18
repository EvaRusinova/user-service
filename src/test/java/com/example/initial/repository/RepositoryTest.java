package com.example.initial.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.example.initial.entity.Comment;
import com.example.initial.entity.Post;
import com.example.initial.entity.User;
import jakarta.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.dao.DataIntegrityViolationException;

@SpringBootTest
public class RepositoryTest {

  @Autowired private UserRepository userRepository;
  @Autowired private PostRepository postRepository;
  @Autowired private CommentRepository commentRepository;
  @Autowired private CacheManager cacheManager;

  private Cache postByAuthorCache;
  private Cache allPostsByUserIdCache;
  private Cache allPostsByAuthorCache;

  private final String userName = "John";

  @BeforeEach
  public void setup() {
    userRepository.deleteAll();
    postRepository.deleteAll();
    commentRepository.deleteAll();
    postByAuthorCache = cacheManager.getCache("postByAuthor");
    allPostsByUserIdCache = cacheManager.getCache("allPostsByUserId");
    allPostsByAuthorCache = cacheManager.getCache("allPostsByAuthor");
  }

  @Test
  public void testSaveUser() {
    // Create a user
    var user =
        User.builder()
            .fullName("John Doe")
            .username("john_doe")
            .password("john_doe")
            .creditCard("4242424242424242")
            .age(25)
            .build();

    // Save the user
    User savedUser = userRepository.save(user);

    // Verify the user is saved correctly
    assertNotNull(savedUser.getId());
    assertEquals("John Doe", savedUser.getFullName());
  }

  @Test
  public void testFindByAuthor() {
    // Create a user
    var user =
        User.builder()
            .fullName("John Doe")
            .username("john_doe")
            .password("john_doe")
            .creditCard("4242424242424242")
            .age(25)
            .build();
    userRepository.save(user);

    // Create a post with the author set to the user's name
    Post post =
        Post.builder()
            .author(user.getFullName())
            .title("Hello World")
            .body("This is my first post")
            .user(user)
            .build();
    postRepository.save(post);

    // Find the post by author
    Post foundPost = postRepository.findByAuthor(user.getFullName());

    // Verify the post is found correctly
    assertNotNull(foundPost);
    assertEquals(post.getId(), foundPost.getId());
    assertEquals(post.getAuthor(), foundPost.getAuthor());
    assertEquals(post.getTitle(), foundPost.getTitle());
    assertEquals(post.getBody(), foundPost.getBody());
  }

  @Test
  public void testFindByAuthorComment() {
    // Create a user
    var user =
        User.builder()
            .fullName("John Doe")
            .username("john_doe")
            .password("john_doe")
            .creditCard("4242424242424242")
            .age(25)
            .build();
    userRepository.save(user);

    // Create a post
    Post post =
        Post.builder()
            .author(user.getFullName())
            .title("Hello World")
            .body("This is my first post")
            .build();
    postRepository.save(post);

    // Create a comment with the author set to the user's name
    Comment comment =
        Comment.builder().author(user.getFullName()).body("Great post!").post(post).build();
    commentRepository.save(comment);

    // Find the comment by author
    Comment foundComment = commentRepository.findByAuthor(user.getFullName());

    // Verify the comment is found correctly
    assertNotNull(foundComment);
    assertEquals(comment.getId(), foundComment.getId());
    assertEquals(comment.getAuthor(), foundComment.getAuthor());
    assertEquals(comment.getBody(), foundComment.getBody());
    assertEquals(comment.getPost().getId(), foundComment.getPost().getId());
  }

  @Test
  public void testFindAllUsers() {
    // Create multiple users
    var user1 =
        User.builder()
            .fullName("John Doe")
            .username("john_doe1")
            .password("john_doe")
            .creditCard("4242424242424242")
            .age(25)
            .build();
    var user2 =
        User.builder()
            .fullName("John Doe")
            .username("john_doe2")
            .password("john_doe")
            .creditCard("4242424242424242")
            .age(25)
            .build();
    userRepository.saveAll(Arrays.asList(user1, user2));

    // Find all users
    List<User> foundUsers = userRepository.findAll();

    // Verify all users are found correctly
    assertEquals(2, foundUsers.size());
  }

  @Test
  public void testFindAllPostsByUserId() {
    // Create a user
    var user =
        User.builder()
            .fullName("John Doe")
            .username("john_doe")
            .password("john_doe")
            .creditCard("4242424242424242")
            .age(25)
            .build();
    userRepository.save(user);

    // Create multiple posts by the user
    Post post1 =
        Post.builder()
            .author(user.getFullName())
            .user(user)
            .title("Post 1")
            .body("Body of post 1")
            .build();
    Post post2 =
        Post.builder()
            .author(user.getFullName())
            .user(user)
            .title("Post 2")
            .body("Body of post 2")
            .build();
    postRepository.saveAll(Arrays.asList(post1, post2));

    // Find all posts by the user
    List<Post> foundPosts = postRepository.findAllByUserId(user.getId());

    // Verify all posts by the user are found correctly
    assertFalse(foundPosts.isEmpty());
    assertEquals(2, foundPosts.size());
    assertEquals(foundPosts.get(0).getAuthor(), user.getFullName());
  }

  @Test
  public void testFindAllCommentsByPost() {
    // Create a post
    Post post =
        Post.builder()
            .author("John Doe")
            .title("Hello World")
            .body("This is my first post")
            .build();
    postRepository.save(post);

    // Create multiple comments on the post
    Comment comment1 = Comment.builder().author("Jane Smith").body("Comment 1").post(post).build();
    Comment comment2 = Comment.builder().author("Alice").body("Comment 2").post(post).build();
    commentRepository.saveAll(Arrays.asList(comment1, comment2));

    // Find all comments on the post
    List<Comment> foundComments = commentRepository.findAllByPost(post);

    // Verify all comments on the post are found correctly
    assertEquals(2, foundComments.size());
  }

  @Test
  public void testDeleteUser() {
    // Create a user
    var user =
        User.builder()
            .fullName("John Doe")
            .username("john_doe")
            .password("john_doe")
            .creditCard("4242424242424242")
            .age(25)
            .build();
    userRepository.save(user);

    // Delete the user
    userRepository.delete(user);

    // Verify the user is deleted
    assertFalse(userRepository.findById(user.getId()).isPresent());
  }

  @Test
  public void testDeletePost() {
    // Create a post
    Post post =
        Post.builder()
            .author("John Doe")
            .title("Hello World")
            .body("This is my first post")
            .build();
    postRepository.save(post);

    // Delete the post
    postRepository.delete(post);

    // Verify the post is deleted
    assertFalse(postRepository.findById(post.getId()).isPresent());
  }

  @Test
  public void testDeleteComment() {
    // Create a comment
    Comment comment = Comment.builder().author("Jane Smith").body("Comment body").build();
    commentRepository.save(comment);

    // Delete the comment
    commentRepository.delete(comment);

    // Verify the comment is deleted
    assertFalse(commentRepository.findById(comment.getId()).isPresent());
  }

  @Test
  public void testCountUsers() {
    // Create multiple users
    var user1 =
        User.builder()
            .fullName("John Doe")
            .username("john_doe1")
            .password("john_doe")
            .creditCard("4242424242424242")
            .age(25)
            .build();
    var user2 =
        User.builder()
            .fullName("John Doe")
            .username("john_doe2")
            .password("john_doe")
            .creditCard("4242424242424242")
            .age(25)
            .build();
    userRepository.saveAll(Arrays.asList(user1, user2));

    // Count the number of users
    long count = userRepository.count();

    // Verify the count is correct
    assertEquals(2, count);
  }

  @Test
  public void testCountPostsByUser() {
    // Create a user
    var user =
        User.builder()
            .fullName("John Doe")
            .username("john_doe")
            .password("john_doe")
            .creditCard("4242424242424242")
            .age(25)
            .build();
    userRepository.save(user);

    // Create multiple posts by the user
    Post post1 =
        Post.builder()
            .author(user.getFullName())
            .title("Post 1")
            .body("Body of post 1")
            .user(user)
            .build();
    Post post2 =
        Post.builder()
            .author(user.getFullName())
            .title("Post 2")
            .body("Body of post 2")
            .user(user)
            .build();
    postRepository.saveAll(Arrays.asList(post1, post2));

    // Count the number of posts by the user
    long count = postRepository.countByUser(user);

    // Verify the count is correct
    assertEquals(2, count);
  }

  @Test
  public void testFindUserById() {
    // Create a user
    var user =
        User.builder()
            .fullName("John Doe")
            .username("john_doe")
            .password("john_doe")
            .creditCard("4242424242424242")
            .age(25)
            .build();
    userRepository.save(user);

    // Find the user by ID
    Optional<User> foundUser = userRepository.findById(user.getId());

    // Verify the user is found correctly
    assertTrue(foundUser.isPresent());
    assertEquals(user.getId(), foundUser.get().getId());
    assertEquals(user.getFullName(), foundUser.get().getFullName());
  }

  @Test
  public void testFindPostById() {
    // Create a post
    Post post =
        Post.builder()
            .author("John Doe")
            .title("Hello World")
            .body("This is my first post")
            .build();
    postRepository.save(post);

    // Find the post by ID
    Optional<Post> foundPost = postRepository.findById(post.getId());

    // Verify the post is found correctly
    assertTrue(foundPost.isPresent());
    assertEquals(post.getId(), foundPost.get().getId());
    assertEquals(post.getAuthor(), foundPost.get().getAuthor());
    assertEquals(post.getTitle(), foundPost.get().getTitle());
  }

  @Test
  public void testFindCommentById() {
    // Create a comment
    Comment comment = Comment.builder().author("Jane Smith").body("Comment body").build();
    commentRepository.save(comment);

    // Find the comment by ID
    Optional<Comment> foundComment = commentRepository.findById(comment.getId());

    // Verify the comment is found correctly
    assertTrue(foundComment.isPresent());
    assertEquals(comment.getId(), foundComment.get().getId());
    assertEquals(comment.getAuthor(), foundComment.get().getAuthor());
    assertEquals(comment.getBody(), foundComment.get().getBody());
  }

  @Test
  public void testFindAllPostsByAuthor() {
    var postAuthor = "John Doe";
    // Create multiple posts
    Post post1 = Post.builder().author(postAuthor).title("Post 1").body("Body of post 1").build();
    Post post2 = Post.builder().author("Jane Smith").title("Post 2").body("Body of post 2").build();
    postRepository.saveAll(Arrays.asList(post1, post2));

    // Find all posts by author "John Doe"
    List<Post> foundPosts = postRepository.findAllByAuthor(postAuthor);

    // Verify all posts by the author are found correctly
    assertEquals(1, foundPosts.size());
    assertEquals(foundPosts.get(0).getAuthor(), postAuthor);
  }

  // ...

  @Test
  public void testFindUserById_NotFound() {
    // Find a non-existent user by ID
    Optional<User> foundUser = userRepository.findById(999L);

    // Verify the user is not found
    assertFalse(foundUser.isPresent());
  }

  @Test
  public void testFindByAuthor_NotFound() {
    // Find a post with a non-existent author
    Post foundPost = postRepository.findByAuthor("Non-existent Author");

    // Verify the post is not found
    assertNull(foundPost);
  }

  @Test
  public void testSavePost_NullAuthor() {
    // Create a post with a null author
    Post post =
        Post.builder().author(null).title("Hello World").body("This is my first post").build();

    // Attempt to save the post
    Post savedPost = postRepository.save(post);

    // Verify the post is saved correctly with a null author
    assertNotNull(savedPost.getId());
    assertNull(savedPost.getAuthor());
    assertEquals("Hello World", savedPost.getTitle());
    assertEquals("This is my first post", savedPost.getBody());
  }

  @Test
  public void testSaveComment_NullAuthor() {
    // Create a comment with a null author
    Comment comment = Comment.builder().author(null).body("Comment body").build();

    // Attempt to save the comment
    Comment savedComment = commentRepository.save(comment);

    // Verify the comment is saved correctly with a null author
    assertNotNull(savedComment.getId());
    assertNull(savedComment.getAuthor());
    assertEquals("Comment body", savedComment.getBody());
  }

  @Test
  public void testSaveUser_DuplicateName() {
    // Create a user with a duplicate name
    var user1 =
        User.builder()
            .fullName("John Doe")
            .username("john_doe")
            .password("john_doe")
            .creditCard("4242424242424242")
            .age(25)
            .build();
    userRepository.save(user1);

    var user2 =
        User.builder()
            .fullName("John Doe")
            .username("john_doe")
            .password("john_doe")
            .creditCard("4242424242424242")
            .age(25)
            .build();

    // Attempt to save the user with the same name
    assertThrows(DataIntegrityViolationException.class, () -> userRepository.save(user2));
  }

  @Test
  public void testSavePost_EmptyTitle() {
    // Create a post with an empty title
    Post post = Post.builder().author("John Doe").title("").body("This is my first post").build();

    // Attempt to save the post
    assertThrows(ConstraintViolationException.class, () -> postRepository.save(post));
  }

  @Test
  public void testSaveComment_EmptyBody() {
    // Create a comment with an empty body
    Comment comment = Comment.builder().author("Jane Smith").body("").build();

    // Attempt to save the comment
    assertThrows(ConstraintViolationException.class, () -> commentRepository.save(comment));
  }

  // ...

  @Test
  public void testSaveUser_NullName() {
    // Create a user with a null name
    User user = User.builder().fullName(null).build();

    // Attempt to save the user
    assertThrows(ConstraintViolationException.class, () -> userRepository.save(user));
  }

  @Test
  public void testSavePost_NullTitle() {
    // Create a post with a null title
    Post post = Post.builder().author("John Doe").title(null).body("This is my first post").build();

    // Attempt to save the post
    assertThrows(ConstraintViolationException.class, () -> postRepository.save(post));
  }

  @Test
  public void testSaveComment_NullBody() {
    // Create a comment with a null body
    Comment comment = Comment.builder().author("Jane Smith").body(null).build();

    // Attempt to save the comment
    assertThrows(ConstraintViolationException.class, () -> commentRepository.save(comment));
  }

  @Test
  public void testFindUserById_InvalidId() {
    // Find a user by an invalid ID
    assertThrows(NoSuchElementException.class, () -> userRepository.findById(999L).orElseThrow());
  }

  @Test
  public void testCachingFindByAuthor() {
    // Clear the cache before the test
    postByAuthorCache.clear();

    // First call should not be cached
    assertNull(postByAuthorCache.get(userName));

    // Second call should be cached
    postRepository.findByAuthor(userName);
    assertNotNull(postByAuthorCache.get(userName));
  }

  @Test
  public void testCachingFindAllByUserId() {
    // Clear the cache before the test
    allPostsByUserIdCache.clear();

    // First call should not be cached
    assertNull(allPostsByUserIdCache.get(1L));

    // Second call should be cached
    postRepository.findAllByUserId(1L);
    assertNotNull(postByAuthorCache.get(userName));
  }

  @Test
  public void testCachingFindAllByAuthor() {
    // Clear the cache before the test
    allPostsByAuthorCache.clear();

    // First call should not be cached
    assertNull(allPostsByAuthorCache.get(userName));

    // Second call should be cached
    postRepository.findAllByAuthor(userName);
    assertNotNull(postByAuthorCache.get(userName));
  }
}
