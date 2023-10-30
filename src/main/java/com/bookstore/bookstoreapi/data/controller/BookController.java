package com.bookstore.bookstoreapi.data.controller;

import com.bookstore.bookstoreapi.data.entity.Book;
import com.bookstore.bookstoreapi.data.service.BookService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/books")
@RestController
public class BookController {

  @Autowired private BookService bookService;

  @PostMapping("/create")
  public ResponseEntity<Book> createABook(@RequestBody Book book) {
    Optional<Book> createdBook = bookService.createABook(book);
    if (createdBook.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(createdBook.get(), HttpStatus.CREATED);
  }

  @GetMapping("/{ISBN}")
  public ResponseEntity<Book> findByISBN(@PathVariable String ISBN) {
    Book book = bookService.findByISBN(ISBN);

    if (book != null) {
      return new ResponseEntity<>(book, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @GetMapping("/{genre}/listBooksByGenre")
  public ResponseEntity<List<Book>> listBooksByGenre(@PathVariable String genre) {

    Optional<List<Book>> listBooksByGenre = bookService.listBooksByGenre(genre);

    return listBooksByGenre
        .map(ResponseEntity::ok)
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
}
