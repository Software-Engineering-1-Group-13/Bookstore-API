package com.bookstore.bookstoreapi.data.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "\"User\"")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "UserID")
  private Long id;

  @Column(name = "Username", nullable = false, unique = true)
  private String username;

  @Column(name = "Password", nullable = false)
  private String password;

  @Column(name = "Name")
  private String name;

  @Column(name = "Email Address", unique = true)
  private String emailAddress;

  @Column(name = "Home Address")
  private String homeAddress;

  @EqualsAndHashCode.Exclude
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  @Builder.Default
  private Set<Comment> comments = new HashSet<>();

  @EqualsAndHashCode.Exclude
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  @Builder.Default
  private Set<Rating> ratings = new HashSet<>();

  @ToString.Exclude
  @Builder.Default
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private Set<Wishlist> wishlists = new HashSet<>();

  @EqualsAndHashCode.Exclude
  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
  private Cart cart;

  @PrePersist
  @PreUpdate
  private void hashPassword() {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    this.password = passwordEncoder.encode(this.password);
  }
}
