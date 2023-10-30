package com.bookstore.bookstoreapi.data.controller;

import com.bookstore.bookstoreapi.data.entity.Comment;
import com.bookstore.bookstoreapi.data.entity.Rating;
import com.bookstore.bookstoreapi.data.service.RatingService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RatingController {

  @Autowired private RatingService ratingService;

  @PostMapping("/rating/book")
  public ResponseEntity<Void> createRating(
      @RequestParam Integer rating, @RequestParam Long userID, @RequestParam Long bookID) {

    Optional<Rating> createdRating = ratingService.createRating(rating, userID, bookID);
    if (createdRating.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PostMapping("/comments/book")
  public ResponseEntity<Void> createComment(
      @RequestParam String comment, @RequestParam Long userID, @RequestParam Long bookID) {

    Optional<Comment> createdComment = ratingService.createComment(comment, userID, bookID);
    if (createdComment.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}
