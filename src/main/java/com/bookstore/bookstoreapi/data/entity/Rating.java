package com.bookstore.bookstoreapi.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Rating")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rating {

  @Column(name = "Timestamp")
  @Builder.Default
  private LocalDate localDate = LocalDate.now();

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "RatingID")
  private Long id;

  @Column(name = "Rating")
  @Min(1)
  @Max(5)
  private Integer rating;

  @ManyToOne
  @JoinColumn(name = "Book")
  private Book book;

  @EqualsAndHashCode.Exclude
  @ManyToOne
  @JoinColumn(name = "UserID", insertable = false, updatable = false)
  private Customer customer;
}
