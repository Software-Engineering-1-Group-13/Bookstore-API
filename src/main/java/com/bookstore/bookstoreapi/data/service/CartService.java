package com.bookstore.bookstoreapi.data.service;

import com.bookstore.bookstoreapi.data.entity.Book;
import com.bookstore.bookstoreapi.data.entity.Cart;
import com.bookstore.bookstoreapi.data.entity.User;
import com.bookstore.bookstoreapi.data.repository.BookRepository;
import com.bookstore.bookstoreapi.data.repository.CartRepository;
import com.bookstore.bookstoreapi.data.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

  @Autowired CartRepository cartRepository;
  @Autowired BookRepository bookRepository;
  @Autowired UserRepository userRepository;

  public Optional<Double> getCartSubtotal(Long cartID) {

    return cartRepository.findCartSubtotal(cartID).describeConstable();
  }

  public Optional<Cart> addBookToCart(Long bookID, Long userID) {

    Optional<Book> bookOptional = bookRepository.findById(bookID);
    Optional<User> userOptional = userRepository.findById(userID);
    if (bookOptional.isEmpty() || userOptional.isEmpty()) {
      return Optional.empty();
    }

    if (userOptional.get().getCart().getBooks().contains(bookOptional.get())) {
      return Optional.empty();
    }

    userOptional.get().getCart().getBooks().add(bookOptional.get());
    cartRepository.save(userOptional.get().getCart());

    return Optional.of(userOptional.get().getCart());
  }

  public Optional<Cart> listBooksInCart(Long userID) {

    Optional<User> userOptional = userRepository.findById(userID);
    return userOptional.map(User::getCart);
  }

  public Optional<Cart> removeBookFromCart(Long bookID, Long userID) {

    Optional<Book> bookOptional = bookRepository.findById(bookID);
    Optional<User> userOptional = userRepository.findById(userID);

    if (bookOptional.isEmpty() || userOptional.isEmpty()) {
      return Optional.empty();
    }

    Book book = bookOptional.get();
    Cart cart = userOptional.get().getCart();

    if (cart.getBooks().contains(book)) {
      cart.getBooks().remove(book);
      book.getCarts().remove(cart);

      cartRepository.save(cart);
      bookRepository.save(book);

      return Optional.of(cart);
    }

    return Optional.empty();
  }
}
