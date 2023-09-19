package com.bookstore.bookstoreapi.data.configuration;

import com.bookstore.bookstoreapi.data.entity.Customer;
import com.bookstore.bookstoreapi.data.entity.Wishlist;
import com.bookstore.bookstoreapi.data.repository.CustomerRepository;
import com.bookstore.bookstoreapi.data.repository.WishlistRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

  @Bean
  public CommandLineRunner initData(
      CustomerRepository customerRepository, WishlistRepository wishlistRepository) {
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
    };
  }
}
