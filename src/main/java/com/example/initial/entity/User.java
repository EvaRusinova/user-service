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
  @Column(name = "name")
  private String name;

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

  @Min(value = 18, message = "Age must be at least 18")
  @Column(name = "age")
  private Integer age;

  @Column(name = "is_active")
  private boolean isActive;

  @CreditCardNumber(message = "Invalid credit card number")
  @NotBlank(message = "Credit card number can not be null")
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
