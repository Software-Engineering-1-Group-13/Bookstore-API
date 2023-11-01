package com.bookstore.bookstoreapi.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.text.SimpleDateFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
    name = "Rating",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"BookID", "UserID"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rating {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "RatingID")
  private Long id;

  @Column(name = "Timestamp")
  @Builder.Default
  private String timeStamp =
      new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date());

  @Column(name = "Rating")
  @Min(1)
  @Max(5)
  private Integer rating;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "BookID")
  private Book book;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "UserID")
  private User user;
}
