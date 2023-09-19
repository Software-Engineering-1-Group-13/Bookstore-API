package com.bookstore.bookstoreapi.data.service;

import com.bookstore.bookstoreapi.data.entity.Customer;
import com.bookstore.bookstoreapi.data.entity.Wishlist;
import com.bookstore.bookstoreapi.data.repository.CustomerRepository;
import com.bookstore.bookstoreapi.data.repository.WishlistRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishlistService {

  @Autowired private WishlistRepository wishlistRepository;
  @Autowired private CustomerRepository customerRepository;

  public Optional<Wishlist> createWishlist(String wishlistName, Long userID) {

    Optional<Customer> userOptional = customerRepository.findById(userID);
    if (userOptional.isEmpty()) {
      return Optional.empty();
    }

    Customer customer = userOptional.get();

    List<Wishlist> existingWishlists =
        wishlistRepository.findByCustomerAndName(customer, wishlistName);
    if (!existingWishlists.isEmpty()) {
      return Optional.empty();
    }

    Wishlist newWishlist = new Wishlist();
    newWishlist.setName(wishlistName);
    newWishlist.setCustomer(customer);
    wishlistRepository.save(newWishlist);

    return Optional.of(newWishlist);
  }
}
