package com.example.initial.entity;

import com.example.initial.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.Collection;
import java.util.List;
import lombok.*;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {

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
  @NotNull
  @Column(name = "user_name", unique = true)
  private String username;

  @Size(min = 2, max = 100, message = "Password must be between 2 and 100 characters")
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

  @Enumerated(EnumType.STRING)
  private Role roles;

  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  public String getUsername() {
    return username;
  }

  public boolean isAccountNonExpired() {
    return true;
  }

  public boolean isAccountNonLocked() {
    return true;
  }

  public boolean isCredentialsNonExpired() {
    return true;
  }

  public boolean isEnabled() {
    return isActive != null && isActive;
  }
}
