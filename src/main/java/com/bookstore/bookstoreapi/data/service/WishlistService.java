package com.bookstore.bookstoreapi.data.service;

import com.bookstore.bookstoreapi.data.entity.Book;
import com.bookstore.bookstoreapi.data.entity.User;
import com.bookstore.bookstoreapi.data.entity.Wishlist;
import com.bookstore.bookstoreapi.data.repository.BookRepository;
import com.bookstore.bookstoreapi.data.repository.UserRepository;
import com.bookstore.bookstoreapi.data.repository.WishlistRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishlistService {

  @Autowired private WishlistRepository wishlistRepository;
  @Autowired private UserRepository userRepository;
  @Autowired private BookRepository bookRepository;

  public Optional<Wishlist> createWishlist(String wishlistName, Long userID) {

    Optional<User> userOptional = userRepository.findById(userID);
    if (userOptional.isEmpty()) {
      return Optional.empty();
    }

    User user = userOptional.get();

    List<Wishlist> existingWishlists = wishlistRepository.findByUserAndName(user, wishlistName);
    if (!existingWishlists.isEmpty()) {
      return Optional.empty();
    }

    Wishlist newWishlist = new Wishlist();
    newWishlist.setName(wishlistName);
    newWishlist.setUser(user);
    wishlistRepository.save(newWishlist);

    return Optional.of(newWishlist);
  }

  public Optional<Wishlist> addBookToWishlist(Long bookID, Long wishlistID) {

    Optional<Book> bookOptional = bookRepository.findById(bookID);
    Optional<Wishlist> wishlistOptional = wishlistRepository.findById(wishlistID);

    if (bookOptional.isEmpty() || wishlistOptional.isEmpty()) {
      return Optional.empty();
    }

    Book book = bookOptional.get();
    Wishlist wishlist = wishlistOptional.get();

    if (wishlist.getBooks().contains(book)) {
      return Optional.empty();
    }

    wishlist.getBooks().add(book);
    book.getWishlists().add(wishlist);

    wishlistRepository.save(wishlist);
    bookRepository.save(book);

    return Optional.of(wishlist);
  }

  public Optional<Wishlist> removeBookFromWishlist(Long bookID, Long wishlistID) {

    Optional<Book> bookOptional = bookRepository.findById(bookID);
    Optional<Wishlist> wishlistOptional = wishlistRepository.findById(wishlistID);

    if (bookOptional.isEmpty() || wishlistOptional.isEmpty()) {
      return Optional.empty();
    }

    Book book = bookOptional.get();
    Wishlist wishlist = wishlistOptional.get();

    if (wishlist.getBooks().contains(book)) {
      wishlist.getBooks().remove(book);
      book.getWishlists().remove(wishlist);

      wishlistRepository.save(wishlist);
      bookRepository.save(book);

      return Optional.of(wishlist);
    }

    return Optional.empty();
  }

  public Optional<Wishlist> listBooksFromWishlist(Long wishlistId) {

    return wishlistRepository.findById(wishlistId);
  }
}
