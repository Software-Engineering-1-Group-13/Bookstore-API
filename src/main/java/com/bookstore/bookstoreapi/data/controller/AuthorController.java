package com.bookstore.bookstoreapi.data.controller;

import com.bookstore.bookstoreapi.data.entity.Author;
import com.bookstore.bookstoreapi.data.service.AuthorService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
public class AuthorController {

  @Autowired private AuthorService authorService;

  @PostMapping("/create")
  public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
    Optional<Author> createdAuthor = authorService.createAuthor(author);
    if (createdAuthor.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(createdAuthor.get(), HttpStatus.CREATED);
  }
}
