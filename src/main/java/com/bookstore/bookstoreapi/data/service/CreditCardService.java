package com.bookstore.bookstoreapi.data.service;

import com.bookstore.bookstoreapi.data.entity.CreditCard;
import com.bookstore.bookstoreapi.data.entity.User;
import com.bookstore.bookstoreapi.data.repository.CreditCardRepository;
import com.bookstore.bookstoreapi.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CreditCardService {

  @Autowired private UserRepository userRepository;

  @Autowired private CreditCardRepository creditCardRepository;

  public void addCreditCardToUser(String username, CreditCard creditCard) {

    User user =
        userRepository
            .findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    creditCard.setUser(user);

    creditCardRepository.save(creditCard);
  }
}
