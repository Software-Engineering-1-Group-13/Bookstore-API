package com.bookstore.bookstoreapi.data.service;

import com.bookstore.bookstoreapi.data.entity.Book;
import com.bookstore.bookstoreapi.data.entity.Comment;
import com.bookstore.bookstoreapi.data.entity.Rating;
import com.bookstore.bookstoreapi.data.entity.User;
import com.bookstore.bookstoreapi.data.repository.BookRepository;
import com.bookstore.bookstoreapi.data.repository.CommentRepository;
import com.bookstore.bookstoreapi.data.repository.RatingRepository;
import com.bookstore.bookstoreapi.data.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

  @Autowired private RatingRepository ratingRepository;
  @Autowired private UserRepository userRepository;
  @Autowired private BookRepository bookRepository;
  @Autowired private CommentRepository commentRepository;

  public Optional<Rating> createRating(Integer rating, Long userID, Long bookID) {

    Optional<User> userOptional = userRepository.findById(userID);
    Optional<Book> bookOptional = bookRepository.findById(bookID);

    if (userOptional.isEmpty() || bookOptional.isEmpty()) {
      return Optional.empty();
    }

    User user = userOptional.get();
    Book book = bookOptional.get();

    Rating newRating = new Rating();

    newRating.setUser(user);
    newRating.setBook(book);
    newRating.setRating(rating);

    ratingRepository.save(newRating);
    updateAverageRating(bookOptional.get());

    user.getRatings().add(newRating);
    userRepository.save(user);

    return Optional.of(newRating);
  }

  private void updateAverageRating(Book book) {

    List<Rating> ratings = ratingRepository.findByBook(book);
    double averageRating = ratings.stream().mapToInt(Rating::getRating).average().orElse(0.0);

    book.setAverageRating(averageRating);
    bookRepository.save(book);
  }

  public Optional<Comment> createComment(String comment, Long userID, Long bookID) {

    Optional<User> userOptional = userRepository.findById(userID);
    Optional<Book> bookOptional = bookRepository.findById(bookID);

    if (userOptional.isEmpty() || bookOptional.isEmpty()) {
      return Optional.empty();
    }

    User user = userOptional.get();
    Book book = bookOptional.get();

    Comment newComment = new Comment();

    newComment.setUser(user);
    newComment.setBook(book);
    newComment.setComment(comment);

    commentRepository.save(newComment);

    user.getComments().add(newComment);
    userRepository.save(user);

    return Optional.of(newComment);
  }
}
