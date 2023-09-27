package com.bookstore.bookstoreapi.data.controller;

import com.bookstore.bookstoreapi.data.entity.Book;
import com.bookstore.bookstoreapi.data.entity.Rating;
import com.bookstore.bookstoreapi.data.entity.Customer;
import com.bookstore.bookstoreapi.data.service.RatingService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RatingController {

    @Autowired private RatingService ratingService;

    @PostMapping("/rating")
    public ResponseEntity<Void> createRating(
            @RequestParam Integer rating, @RequestParam Long userID, @RequestParam Long bookID) {

        Optional<Rating> createdRating = ratingService.createRating(rating, userID, bookID);
        if (createdRating.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}