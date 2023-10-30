package com.bookstore.bookstoreapi.data.controller;

import com.bookstore.bookstoreapi.data.entity.Book;
import com.bookstore.bookstoreapi.data.entity.Cart;
import com.bookstore.bookstoreapi.data.service.CartService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

  @PostMapping("/addBook")
  public ResponseEntity<Void> addBookToCart(@RequestParam Long bookID, @RequestParam Long userID) {

    Optional<Cart> addBookToCart = cartService.addBookToCart(bookID, userID);
    if (addBookToCart.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/{userID}/listBooks")
  public ResponseEntity<List<Book>> listBooksInCart(@PathVariable Long userID) {

    Optional<Cart> listBooksInCart = cartService.listBooksInCart(userID);

    return listBooksInCart
        .<ResponseEntity<List<Book>>>map(
            cart -> ResponseEntity.ok(new ArrayList<>(cart.getBooks())))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }
}
