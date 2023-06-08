package com.example.initial.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
  @NotBlank(message = "Name of the user can not be null")
  @Column(name = "name")
  private String name;

  @OneToMany(
      mappedBy = "user",
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      fetch = FetchType.EAGER)
  @ToString.Exclude
  private List<Post> posts;

  @Size(min = 5, max = 20, message = "User name must be between 5 and 20 characters")
  @NotBlank(message = "User name can not be null")
  @Column(name = "user_name", unique = true)
  private String userName;

  @Size(min = 2, max = 50, message = "Password must be between 2 and 50 characters")
  @NotBlank(message = "Password can not be null")
  @Column(name = "password")
  private String password;
}
