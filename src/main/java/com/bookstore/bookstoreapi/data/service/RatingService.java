package com.bookstore.bookstoreapi.data.service;

import com.bookstore.bookstoreapi.data.entity.Book;
import com.bookstore.bookstoreapi.data.entity.Comment;
import com.bookstore.bookstoreapi.data.entity.Customer;
import com.bookstore.bookstoreapi.data.entity.Rating;
import com.bookstore.bookstoreapi.data.repository.BookRepository;
import com.bookstore.bookstoreapi.data.repository.CommentRepository;
import com.bookstore.bookstoreapi.data.repository.CustomerRepository;
import com.bookstore.bookstoreapi.data.repository.RatingRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

  @Autowired private RatingRepository ratingRepository;
  @Autowired private CustomerRepository customerRepository;
  @Autowired private BookRepository bookRepository;
  @Autowired private CommentRepository commentRepository;

  public Optional<Rating> createRating(Integer rating, Long userID, Long bookID) {

    Optional<Customer> userOptional = customerRepository.findById(userID);
    Optional<Book> bookOptional = bookRepository.findById(bookID);

    if (userOptional.isEmpty() || bookOptional.isEmpty()) {
      return Optional.empty();
    }

    Customer customer = userOptional.get();
    Book book = bookOptional.get();

    Rating newRating = new Rating();

    newRating.setCustomer(customer);
    newRating.setBook(book);
    newRating.setRating(rating);

    ratingRepository.save(newRating);
    updateAverageRating(bookOptional.get());

    customer.getRatings().add(newRating);
    customerRepository.save(customer);

    return Optional.of(newRating);
  }

  private void updateAverageRating(Book book) {

    List<Rating> ratings = ratingRepository.findByBook(book);
    double averageRating = ratings.stream().mapToInt(Rating::getRating).average().orElse(0.0);

    book.setAverageRating(averageRating);
    bookRepository.save(book);
  }

  public Optional<Comment> createComment(String comment, Long userID, Long bookID) {

    Optional<Customer> userOptional = customerRepository.findById(userID);
    Optional<Book> bookOptional = bookRepository.findById(bookID);

    if (userOptional.isEmpty() || bookOptional.isEmpty()) {
      return Optional.empty();
    }

    Customer customer = userOptional.get();
    Book book = bookOptional.get();

    Comment newComment = new Comment();

    newComment.setCustomer(customer);
    newComment.setBook(book);
    newComment.setComment(comment);

    commentRepository.save(newComment);

    customer.getComments().add(newComment);
    customerRepository.save(customer);

    return Optional.of(newComment);
  }
}
