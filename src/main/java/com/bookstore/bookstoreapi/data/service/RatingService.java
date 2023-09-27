package com.bookstore.bookstoreapi.data.service;

import com.bookstore.bookstoreapi.data.entity.Book;
import com.bookstore.bookstoreapi.data.entity.Customer;
import com.bookstore.bookstoreapi.data.entity.Wishlist;
import com.bookstore.bookstoreapi.data.entity.Rating;
import com.bookstore.bookstoreapi.data.repository.BookRepository;
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
        customer.getRatings().add(newRating);
        customerRepository.save(customer);

        return Optional.of(newRating);
    }
}

