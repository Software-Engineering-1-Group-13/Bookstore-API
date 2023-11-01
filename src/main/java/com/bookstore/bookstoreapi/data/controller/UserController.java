package com.bookstore.bookstoreapi.data.controller;

import com.bookstore.bookstoreapi.data.entity.User;
import com.bookstore.bookstoreapi.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired UserService userService;

  @PostMapping("/create")
  public ResponseEntity<Void> createUser(@RequestBody User user) {

    userService.createUser(user);

    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}
