package com.bookstore.bookstoreapi.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "Book")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "BookID")
  private Long id;

  @Column(name = "Title", nullable = false, unique = true)
  private String title;

  @Column(name = "ISBN", nullable = false, unique = true)
  private String isbn;

  @Temporal(TemporalType.DATE)
  @Column(name = "PublicationDate", nullable = false)
  private Date publishingDate;

  @Column(name = "Price", nullable = false)
  private Double price;

  @Column(name = "StockCount", nullable = false)
  private Integer stockCount;

  @ToString.Exclude
  @Builder.Default
  @ManyToMany
  @JoinTable(
      name = "WishlistBook",
      joinColumns = @JoinColumn(name = "BookID"),
      inverseJoinColumns = @JoinColumn(name = "WishlistID"))
  private List<Wishlist> wishlists = new ArrayList<>();
}
