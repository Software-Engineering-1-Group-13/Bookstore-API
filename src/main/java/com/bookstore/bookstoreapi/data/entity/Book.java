package com.bookstore.bookstoreapi.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import java.util.HashSet;
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

  @Column(name = "Title", nullable = false)
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
  @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
  @Builder.Default
  private Set<Comment> comments = new HashSet<>();

  @ToString.Exclude
  @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
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
}
