package com.bookstore.bookstoreapi.data.configuration;

import com.bookstore.bookstoreapi.data.entity.Book;
import com.bookstore.bookstoreapi.data.entity.Customer;
import com.bookstore.bookstoreapi.data.entity.Wishlist;
import com.bookstore.bookstoreapi.data.repository.BookRepository;
import com.bookstore.bookstoreapi.data.repository.CustomerRepository;
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
          BookRepository bookRepository) {
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
      book1.setIsbn("978-1503215153");
      book1.setTitle("A Journey to the Center of the Earth");
      book1.setAuthor("Jules Verne");
      book1.setGenre("Science Fiction");
      book1.setDescription(
              "A geology professor and his nephew discover and decode an ancient document that"
                      + " shows that a dormant volcano holds a secret entrance to a subterranean world at the earth's center.");
      book1.setPublisher("Simon & Shuster");
      book1.setPublishingDate(DATE_FORMAT.parse("1864-11-25"));
      book1.setPrice(12.99);
      book1.setStockCount(50);