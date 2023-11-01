package com.bookstore.bookstoreapi.data.controller;

import com.bookstore.bookstoreapi.data.entity.CreditCard;
import com.bookstore.bookstoreapi.data.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/credit-card")
public class CreditCardController {

  @Autowired private CreditCardService creditCardService;

  @PostMapping("/add")
  public ResponseEntity<Void> addCreditCardToUser(
      @RequestParam String username, @RequestBody CreditCard creditCard) {

    creditCardService.addCreditCardToUser(username, creditCard);

    return ResponseEntity.ok().build();
  }
}
