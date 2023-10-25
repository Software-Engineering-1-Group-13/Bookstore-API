package com.bookstore.bookstoreapi.data.service;

import com.bookstore.bookstoreapi.data.entity.Book;
import com.bookstore.bookstoreapi.data.repository.BookRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

  @Autowired private BookRepository bookRepository;

  public Optional<Book> createABook(Book book) {
    Book creadtedBook = bookRepository.save(book);
    return Optional.of(creadtedBook);
  }

  public Book getBookByISBN(String isbn) {
    return bookRepository.findByIsbn(isbn);
  }
}
