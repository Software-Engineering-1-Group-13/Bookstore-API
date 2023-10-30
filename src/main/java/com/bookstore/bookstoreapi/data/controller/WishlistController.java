package com.bookstore.bookstoreapi.data.controller;

import com.bookstore.bookstoreapi.data.entity.Book;
import com.bookstore.bookstoreapi.data.entity.Wishlist;
import com.bookstore.bookstoreapi.data.service.WishlistService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

  @Autowired private WishlistService wishlistService;

  @PostMapping("/create")
  public ResponseEntity<Void> createWishlist(
      @RequestParam String wishlistName, @RequestParam Long userID) {

    Optional<Wishlist> createdWishlist = wishlistService.createWishlist(wishlistName, userID);
    if (createdWishlist.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PostMapping("/book")
  public ResponseEntity<Void> addBookToWishlist(
      @RequestParam Long bookID, @RequestParam Long wishlistID) {

    Optional<Wishlist> addedBookToWishlist = wishlistService.addBookToWishlist(bookID, wishlistID);
    if (addedBookToWishlist.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping("/removeBook")
  public ResponseEntity<Void> removeBookFromWishlist(
      @RequestParam Long bookID, @RequestParam Long wishlistID) {

    Optional<Wishlist> removedBookFromWishlist =
        wishlistService.removeBookFromWishlist(bookID, wishlistID);
    if (removedBookFromWishlist.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/{wishlistID}/books")
  public ResponseEntity<List<Book>> listBooksFromWishlist(@PathVariable Long wishlistID) {

    Optional<Wishlist> listedBooksFromWishlist = wishlistService.listBooksFromWishlist(wishlistID);
    return listedBooksFromWishlist
        .<ResponseEntity<List<Book>>>map(
            wishlist -> ResponseEntity.ok(new ArrayList<>(wishlist.getBooks())))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }
}
