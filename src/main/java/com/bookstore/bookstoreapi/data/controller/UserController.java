package com.bookstore.bookstoreapi.data.controller;

import com.bookstore.bookstoreapi.data.dto.UserUpdateDto;
import com.bookstore.bookstoreapi.data.entity.User;
import com.bookstore.bookstoreapi.data.service.UserService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

  @GetMapping("/getUserDetails/{username}")
  public ResponseEntity<User> getUserDetailsByUsername(@PathVariable String username) {

    Optional<User> user = userService.getUserDetailsByUsername(username);

    return user.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PutMapping("/update/{username}")
  public ResponseEntity<Void> updateUser(
      @PathVariable String username, @RequestBody UserUpdateDto userUpdateDto) {

    userService.updateUser(username, userUpdateDto);

    return ResponseEntity.ok().build();
  }
}
