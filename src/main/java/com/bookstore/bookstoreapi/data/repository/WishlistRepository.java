package com.bookstore.bookstoreapi.data.repository;

import com.bookstore.bookstoreapi.data.entity.Customer;
import com.bookstore.bookstoreapi.data.entity.Wishlist;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

  /**
   * @implNote The beauty of Spring Data JPA is that you don't have to manually write the query for
   *     most standard operations. The framework generates the implementation for you based on the
   *     method name. In this case, it'll generate a query that fetches wishlists based on the
   *     associated customer and the wishlist name.
   */
  List<Wishlist> findByCustomerAndName(Customer customer, String wishlistName);
}
