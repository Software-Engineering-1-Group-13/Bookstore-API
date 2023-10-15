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
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Entity
@Table(name = "Book")
@Data
@EqualsAndHashCode(of = {"id", "title"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ResponseStatus(HttpStatus.CREATED)
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "BookID")
  private Long id;

  @Column(name = "ISBN", nullable = false, unique = true)
  private String isbn;

  @Column(name = "Title", nullable = false)
  private String title;

  @Column(name = "Author", nullable = false)
  private String author;

  @Column(name = "Genre", nullable = false)
  private String genre;

  @Column(name = "Description", nullable = false)
  private String description;

  @Column(name = "Publisher", nullable = false)
  private String publisher;

  @Temporal(TemporalType.DATE)
  @Column(name = "PublicationDate", nullable = false)
  private Date publishingDate;

  @Column(name = "Price", nullable = false)
  private Double price;

  @Column(name = "StockCount", nullable = false)
  private Integer stockCount;

  @Column(name = "CopiesSold", nullable = false)
  private Integer copiesSold;

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
