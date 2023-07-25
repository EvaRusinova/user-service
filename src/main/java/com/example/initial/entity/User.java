package com.example.initial.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.*;
import org.hibernate.validator.constraints.CreditCardNumber;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {

  @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
  @NotBlank(message = "Name of the user can not be null")
  @Column(name = "full_name")
  private String fullName;

  @OneToMany(
      mappedBy = "user",
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      fetch = FetchType.EAGER)
  @ToString.Exclude
  @JsonIgnoreProperties("user")
  private List<Post> posts;

  @Size(min = 5, max = 20, message = "User name must be between 5 and 20 characters")
  @NotBlank(message = "User name can not be null")
  @Column(name = "user_name", unique = true)
  private String userName;

  @Size(min = 2, max = 50, message = "Password must be between 2 and 50 characters")
  @NotBlank(message = "Password can not be null")
  @Column(name = "password")
  private String password;

  @Email(message = "Invalid email format")
  @Column(name = "email", unique = true)
  private String email;

  @Min(value = 18, message = "User must be 18 or older to register")
  @Column(name = "age")
  private Integer age;

  @Column(name = "is_active")
  private Boolean isActive;

  @Column(name = "email_verified")
  private Boolean isEmailVerified = false;

  @CreditCardNumber(message = "Invalid credit card number")
  @Column(name = "credit_card")
  private String creditCard;

  @OneToMany(
      mappedBy = "user",
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      fetch = FetchType.EAGER)
  @JsonIgnoreProperties("user")
  private List<Photo> photos;
}
