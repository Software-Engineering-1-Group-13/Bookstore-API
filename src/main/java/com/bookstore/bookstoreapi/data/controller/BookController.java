package com.bookstore.bookstoreapi.data.controller;

import com.bookstore.bookstoreapi.data.entity.Book;
import com.bookstore.bookstoreapi.data.service.BookService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {

  @Autowired private BookService bookService;

  @PostMapping("/books/create")
  public ResponseEntity<Book> createABook(@RequestBody Book book) {
    Optional<Book> createdBook = bookService.createABook(book);
    if (createdBook.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(createdBook.get(), HttpStatus.CREATED);
  }

  @GetMapping("/books/{ISBN}")
  public ResponseEntity<Book> findByISBN(@PathVariable String ISBN) {
    Book book = bookService.findByISBN(ISBN);

    if (book != null) {
      return new ResponseEntity<>(book, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
