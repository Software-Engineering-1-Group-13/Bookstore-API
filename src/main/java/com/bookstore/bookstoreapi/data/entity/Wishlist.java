package com.bookstore.bookstoreapi.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
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

  @ManyToOne
  @JoinColumn(name = "UserID", insertable = false, updatable = false)
  private Customer customer;

  @ToString.Exclude
  @Builder.Default
  @ManyToMany(mappedBy = "wishlists")
  private List<Book> books = new ArrayList<>();
}
