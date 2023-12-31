package com.bookstore.bookstoreapi.data.service;

import com.bookstore.bookstoreapi.data.entity.Book;
import com.bookstore.bookstoreapi.data.repository.BookRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class BookService {

  @Autowired private BookRepository bookRepository;

  public Optional<Book> createABook(Book book) {

    Book creadtedBook = bookRepository.save(book);

    return Optional.of(creadtedBook);
  }

  public List<Book> findByIsbn(String isbn) {

    return bookRepository.findByIsbn(isbn);
  }

  public List<Book> getBooksByAuthorId(Long authorId) {

    return bookRepository.findByAuthorId(authorId);
  }

  public Optional<List<Book>> listBooksByGenre(String genre) {

    return bookRepository.findByGenre(genre);
  }

  public List<Book> getTop10Books() {

    return bookRepository.findTop10ByOrderByCopiesSoldDesc(PageRequest.of(0, 10));
  }

  public List<Book> getBooksByRating(Double rating) {

    return bookRepository.findByAverageRatingGreaterThanEqual(rating);
  }

  public Optional<Book> findBookId(Long bookId) {

    return bookRepository.findById(bookId);
  }

  public Optional<List<Book>> discountBooksOfPublisher(Double discountRate, String publisherName) {

    List<Book> books = bookRepository.findByPublisher(publisherName);
    if (books.isEmpty()) {
      return Optional.empty();
    }

    books.forEach(
        book -> {
          double originalPrice = book.getPrice();
          double discountPrice = originalPrice * (1 - (discountRate / 100));
          book.setPrice(discountPrice);
        });

    bookRepository.saveAll(books);

    return Optional.of(books);
  }
}
