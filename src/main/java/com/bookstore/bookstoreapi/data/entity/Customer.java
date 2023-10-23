package com.bookstore.bookstoreapi.data.entity;

import jakarta.persistence.*;

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
@Table(name = "Customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "CustomerID")
  private Long id;

  @Column(name = "Username", nullable = false, unique = true)
  private String username;

  @Column(name = "Password", nullable = false)
  private String password;

  @Column(name = "Name")
  private String name;

  @Column(name = "Email Address", unique = true)
  private String email;

  @Column(name = "Home Address")
  private String address;

  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
  @Builder.Default
  private Set<Comment> comments = new HashSet<>();

  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
  @Builder.Default
  private Set<Rating> ratings = new HashSet<>();

  @ToString.Exclude
  @Builder.Default
  @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
  private Set<Wishlist> wishlists = new HashSet<>();

  @PrePersist
  @PreUpdate
  private void hashPassword() {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    this.password = passwordEncoder.encode(this.password);
  }
}
