package com.bookstore.bookstoreapi.data.repository;

import com.bookstore.bookstoreapi.data.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

  @Query("SELECT SUM(b.price) FROM Cart c JOIN c.books b WHERE c.id = :cartID")
  Double findCartSubtotal(@Param("cartID") Long cartID);
}
