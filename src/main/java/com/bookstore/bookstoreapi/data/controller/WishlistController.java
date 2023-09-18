package com.bookstore.bookstoreapi.data.controller;

import com.bookstore.bookstoreapi.data.entity.Wishlist;
import com.bookstore.bookstoreapi.data.service.WishlistService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WishlistController {

  @Autowired private WishlistService wishlistService;

  @PostMapping("/wishlist")
  public ResponseEntity<Void> createWishlist(
      @RequestParam String wishlistName, @RequestParam Long userID) {

    Optional<Wishlist> createdWishlist = wishlistService.createWishlist(wishlistName, userID);
    if (createdWishlist.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}
