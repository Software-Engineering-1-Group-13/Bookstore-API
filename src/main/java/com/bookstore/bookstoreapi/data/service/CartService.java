package com.bookstore.bookstoreapi.data.service;

import com.bookstore.bookstoreapi.data.repository.CartRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

  @Autowired CartRepository cartRepository;

  public Optional<Double> getCartSubtotal(Long cartID) {

    return cartRepository.findCartSubtotal(cartID).describeConstable();
  }
}
