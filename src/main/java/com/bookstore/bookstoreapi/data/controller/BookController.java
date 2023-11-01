package com.bookstore.bookstoreapi.data.controller;

import com.bookstore.bookstoreapi.data.entity.Book;
import com.bookstore.bookstoreapi.data.entity.Comment;
import com.bookstore.bookstoreapi.data.service.BookService;
import java.util.ArrayList;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/books")
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

  @GetMapping("/{isbn}")
  public ResponseEntity<List<Book>> findByIsbn(@PathVariable String isbn) {

    List<Book> books = bookService.findByIsbn(isbn);

    if (books.isEmpty()) {

      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(books, HttpStatus.OK);
  }

  @GetMapping("/byAuthor/{authorId}")
  public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable Long authorId) {

    List<Book> books = bookService.getBooksByAuthorId(authorId);

    if (books.isEmpty()) {

      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(books, HttpStatus.OK);
  }

  @GetMapping("/{genre}/listBooksByGenre")
  public ResponseEntity<List<Book>> listBooksByGenre(@PathVariable String genre) {

    Optional<List<Book>> listBooksByGenre = bookService.listBooksByGenre(genre);

    return listBooksByGenre
        .map(ResponseEntity::ok)
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @GetMapping("/top10")
  public ResponseEntity<List<Book>> getTop10Books() {

    List<Book> top10Books = bookService.getTop10Books();

    return ResponseEntity.ok(top10Books);
  }

  @GetMapping("/rating/{rating}")
  public ResponseEntity<List<Book>> getBooksByRating(@PathVariable Double rating) {

    List<Book> books = bookService.getBooksByRating(rating);

    return ResponseEntity.ok(books);
  }

  @GetMapping("/{bookID}/comments")
  public ResponseEntity<List<Comment>> listCommentsFromBook(@PathVariable Long bookID) {

    Optional<Book> findBook = bookService.findBookId(bookID);
    if (findBook.isEmpty()) {

      return ResponseEntity.notFound().build();
    }
    Book book = findBook.get();

    return ResponseEntity.ok(new ArrayList<>(book.getComments()));
  }

  @PutMapping("/author/discountBooks")
  public ResponseEntity<Void> discountBooksOfPublisher(
      @RequestParam Double discountRate, @RequestParam String publisherName) {

    Optional<List<Book>> discountBooksOfPublisher =
        bookService.discountBooksOfPublisher(discountRate, publisherName);
    if (discountBooksOfPublisher.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
