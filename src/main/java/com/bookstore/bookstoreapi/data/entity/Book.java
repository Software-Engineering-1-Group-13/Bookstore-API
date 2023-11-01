package com.bookstore.bookstoreapi.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "Book")
@Data
@EqualsAndHashCode(of = {"id", "title"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "BookID")
  private Long id;

  @Column(name = "ISBN", nullable = false, unique = true)
  private String isbn;

  @Column(name = "Title", nullable = false)
  private String title;

  @ManyToOne
  @JoinColumn(name = "AuthorID")
  private Author author;

  @Column(name = "Genre", nullable = false)
  private String genre;

  @Column(name = "Description", nullable = false)
  private String description;

  @Column(name = "Publisher", nullable = false)
  private String publisher;

  @Column(name = "PublicationDate", nullable = false)
  private LocalDate publishingDate;

  @Column(name = "Price", nullable = false)
  private Double price;

  @Column(name = "StockCount", nullable = false)
  private Integer stockCount;

  @Column(name = "AverageRating")
  private Double averageRating;

  @Column(name = "CopiesSold", nullable = false)
  @Builder.Default
  @Min(value = 0, message = "CopiesSold should not be less than 0")
  private Integer copiesSold = 0;

  @ToString.Exclude
  @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
  @Builder.Default
  private List<Comment> comments = new ArrayList<>();

  @ToString.Exclude
  @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @Builder.Default
  private Set<Rating> ratings = new HashSet<>();

  @JsonIgnore
  @ToString.Exclude
  @Builder.Default
  @ManyToMany
  @JoinTable(
      name = "WishlistBook",
      joinColumns = @JoinColumn(name = "BookID"),
      inverseJoinColumns = @JoinColumn(name = "WishlistID"))
  @JsonManagedReference
  private Set<Wishlist> wishlists = new HashSet<>();

  @JsonIgnore
  @ToString.Exclude
  @Builder.Default
  @ManyToMany(mappedBy = "books")
  private Set<Cart> carts = new HashSet<>();
}
