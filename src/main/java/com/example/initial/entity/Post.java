package com.example.initial.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.*;

@Entity
@Table(name = "post")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post extends BaseEntity {

  @Column(name = "author")
  private String author;

  @NotBlank(message = "Post title can't be empty")
  @Column(name = "title")
  private String title;

  @NotBlank(message = "Post body can't be empty")
  @Column(name = "body")
  private String body;

  @Column(name = "likes")
  private Long likes;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  @ToString.Exclude
  private User user;

  @OneToMany(
      mappedBy = "post",
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      fetch = FetchType.EAGER)
  @ToString.Exclude
  @JsonIgnoreProperties("post")
  private List<Comment> comments;

  @OneToMany(
      mappedBy = "post",
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      fetch = FetchType.EAGER)
  @ToString.Exclude
  @JsonIgnoreProperties("post")
  private List<Photo> photos;
}
