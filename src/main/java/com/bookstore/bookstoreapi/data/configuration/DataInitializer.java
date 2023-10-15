package com.bookstore.bookstoreapi.data.configuration;

import com.bookstore.bookstoreapi.data.entity.Book;
import com.bookstore.bookstoreapi.data.entity.Comment;
import com.bookstore.bookstoreapi.data.entity.Customer;
import com.bookstore.bookstoreapi.data.entity.Rating;
import com.bookstore.bookstoreapi.data.entity.Wishlist;
import com.bookstore.bookstoreapi.data.repository.BookRepository;
import com.bookstore.bookstoreapi.data.repository.CommentRepository;
import com.bookstore.bookstoreapi.data.repository.CustomerRepository;
import com.bookstore.bookstoreapi.data.repository.RatingRepository;
import com.bookstore.bookstoreapi.data.repository.WishlistRepository;
import java.text.SimpleDateFormat;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

  private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

  @Bean
  public CommandLineRunner initData(
      CustomerRepository customerRepository,
      WishlistRepository wishlistRepository,
      BookRepository bookRepository,
      CommentRepository commentRepository,
      RatingRepository ratingRepository) {
    return args -> {
      Customer john = new Customer();
      john.setFirstName("John");
      john.setLastName("Doe");
      john.setEmail("john.doe@example.com");
      john.setAddress("12345 Random st");
      customerRepository.save(john);

      Customer jane = new Customer();
      jane.setFirstName("Jane");
      jane.setLastName("Smith");
      jane.setEmail("jane.smith@example.com");
      jane.setAddress("12345 non-Random st");
      customerRepository.save(jane);

      Wishlist johnWishList = new Wishlist();
      johnWishList.setName("John's Favorites");
      johnWishList.setCustomer(john);
      wishlistRepository.save(johnWishList);

      Book book1 = new Book();
      book1.setTitle("A Journey to the Center of the Earth");
      book1.setIsbn("978-1503215153");
      book1.setPublishingDate(DATE_FORMAT.parse("1864-01-01"));
      book1.setPrice(12.99);
      book1.setStockCount(50);

      Book book2 = new Book();
      book2.setTitle("The Time Machine");
      book2.setIsbn("978-0451530707");
      book2.setPublishingDate(DATE_FORMAT.parse("1895-05-07"));
      book2.setPrice(10.50);
      book2.setStockCount(30);

      Book book3 = new Book();
      book3.setTitle("Brave New World");
      book3.setIsbn("978-0060850524");
      book3.setPublishingDate(DATE_FORMAT.parse("1932-08-15"));
      book3.setPrice(15.20);
      book3.setStockCount(20);

      bookRepository.save(book1);
      bookRepository.save(book2);
      bookRepository.save(book3);

      johnWishList.getBooks().add(book1);
      book1.getWishlists().add(johnWishList);

      johnWishList.getBooks().add(book2);
      book2.getWishlists().add(johnWishList);

      wishlistRepository.save(johnWishList);

      Rating rating1 = new Rating();
      rating1.setCustomer(john);
      rating1.setBook(book1);
      rating1.setRating(3);

      Comment comment1 = new Comment();
      comment1.setCustomer(john);
      comment1.setBook(book1);
      comment1.setComment("Wow! Super awesome book!");

      ratingRepository.save(rating1);
      commentRepository.save(comment1);

      john.getRatings().add(rating1);
      book1.getRatings().add(rating1);
      john.getComments().add(comment1);
      book1.getComments().add(comment1);

      customerRepository.save(john);
      bookRepository.save(book1);
      bookRepository.save(book2);
    };
  }
}
