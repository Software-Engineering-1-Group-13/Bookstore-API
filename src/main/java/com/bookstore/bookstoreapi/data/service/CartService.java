package com.bookstore.bookstoreapi.data.service;

import com.bookstore.bookstoreapi.data.entity.Book;
import com.bookstore.bookstoreapi.data.entity.Cart;
import com.bookstore.bookstoreapi.data.entity.Customer;
import com.bookstore.bookstoreapi.data.repository.BookRepository;
import com.bookstore.bookstoreapi.data.repository.CartRepository;
import com.bookstore.bookstoreapi.data.repository.CustomerRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

  @Autowired CartRepository cartRepository;
  @Autowired BookRepository bookRepository;
  @Autowired CustomerRepository customerRepository;

  public Optional<Double> getCartSubtotal(Long cartID) {

    return cartRepository.findCartSubtotal(cartID).describeConstable();
  }

  public Optional<Cart> addBookToCart(Long bookID, Long userID) {

    Optional<Book> bookOptional = bookRepository.findById(bookID);
    Optional<Customer> customerOptional = customerRepository.findById(userID);
    if (bookOptional.isEmpty() || customerOptional.isEmpty()) {
      return Optional.empty();
    }

    if (customerOptional.get().getCart().getBooks().contains(bookOptional.get())) {
      return Optional.empty();
    }

    customerOptional.get().getCart().getBooks().add(bookOptional.get());
    cartRepository.save(customerOptional.get().getCart());

    return Optional.of(customerOptional.get().getCart());
  }

  public Optional<Cart> listBooksInCart(Long userID) {

    Optional<Customer> customerOptional = customerRepository.findById(userID);
    return customerOptional.map(Customer::getCart);
  }
}
