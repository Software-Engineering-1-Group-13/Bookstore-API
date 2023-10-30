package com.bookstore.bookstoreapi.data.controller;

import com.bookstore.bookstoreapi.data.service.CartService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

  @Autowired CartService cartService;

  @GetMapping("/{cartID}/subtotal")
  public ResponseEntity<Double> getCartSubtotal(@PathVariable Long cartID) {

    Optional<Double> getCartSubtotal = cartService.getCartSubtotal(cartID);
    return getCartSubtotal
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }
}
