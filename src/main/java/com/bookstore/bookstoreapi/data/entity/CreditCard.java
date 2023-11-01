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
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CreditCard")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreditCard {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "CreditCardID")
  private Long id;

  @Column(name = "CreditCardNumber", nullable = false, unique = true)
  @Pattern(regexp = "\\d{16}", message = "Credit Card number must be 16 digits")
  private String creditCardNumber;

  @Column(name = "ExpirationDate", nullable = false)
  private LocalDate expirationDate;

  @Column(name = "CCV", nullable = false)
  @Pattern(regexp = "\\d{3}", message = "CCV must be 3 digits")
  private String ccv;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "UserID", nullable = false)
  private User user;
}
