package com.bookstore.bookstoreapi.data.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

  @Column(name = "FirstName", nullable = false)
  private String firstName;

  @Column(name = "LastName", nullable = false)
  private String lastName;

  @Column(name = "Email", nullable = false, unique = true)
  private String email;

  @Column(name = "Address", nullable = false)
  private String address;

  @ToString.Exclude
  @Builder.Default
  @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
  private List<Wishlist> wishlists = new ArrayList<>();
}
