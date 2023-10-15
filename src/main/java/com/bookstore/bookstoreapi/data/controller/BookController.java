package com.bookstore.bookstoreapi.data.controller;

import com.bookstore.bookstoreapi.data.entity.Book;
import com.bookstore.bookstoreapi.data.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

  @Autowired private BookService bookService;

  @PostMapping(value = "/books", consumes = "application/json")
  public Book createABook(@RequestBody Book book) {
    Book createdBook = bookService.createABook(book).orElse(null);
    return bookService.createABook(book).orElse(null);
  }
}
