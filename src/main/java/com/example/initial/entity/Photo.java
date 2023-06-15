package com.example.initial.entity;

import com.example.initial.enums.PhotoFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "photo")
public class Photo extends BaseEntity {

  @NotBlank(message = "Photo data can not be null")
  @Column(name = "data")
  private String data;

  @Enumerated(EnumType.STRING)
  @Column(name = "format")
  private PhotoFormat format;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id")
  @ToString.Exclude
  private Post post;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  @ToString.Exclude
  private User user;
}
