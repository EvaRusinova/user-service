package com.example.initial.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "comment")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseEntity {

  @Column(name = "author")
  private String author;

  @NotBlank(message = "Comment body can not be empty")
  @Column(name = "body")
  private String body;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id")
  @ToString.Exclude
  @JsonIgnoreProperties
  private Post post;
}
