package com.example.initial.entity;

import com.example.initial.enums.PhotoFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

  @Lob
  @JsonIgnore
  @NotNull(message = "Photo must be provided")
  @Column(name = "data", columnDefinition = "BLOB")
  private byte[] data;

  @NotBlank(message = "Photo name can not be null")
  @Column(name = "name")
  private String name;

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
