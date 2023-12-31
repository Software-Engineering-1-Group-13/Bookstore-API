package com.bookstore.bookstoreapi.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "Wishlist")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Wishlist {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "WishlistID")
  private Long id;

  @Column(name = "WishlistName", nullable = false, unique = true)
  private String name;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "UserID", insertable = false, updatable = false)
  private User user;

  @ToString.Exclude
  @Builder.Default
  @ManyToMany(mappedBy = "wishlists")
  @JsonBackReference
  private Set<Book> books = new HashSet<>();
}
