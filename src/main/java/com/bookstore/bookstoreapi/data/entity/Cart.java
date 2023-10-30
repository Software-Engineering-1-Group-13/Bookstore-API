package com.bookstore.bookstoreapi.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "Cart")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "CartID")
  private Long id;

  @EqualsAndHashCode.Exclude
  @OneToOne
  @JoinColumn(name = "CustomerID")
  private Customer customer;

  @ToString.Exclude
  @Builder.Default
  @ManyToMany
  @JoinTable(
      name = "CartBook",
      joinColumns = @JoinColumn(name = "CardID"),
      inverseJoinColumns = @JoinColumn(name = "BookID"))
  private Set<Book> books = new HashSet<>();
}
