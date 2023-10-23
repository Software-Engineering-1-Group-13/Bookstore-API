package com.bookstore.bookstoreapi.data.service;

import com.bookstore.bookstoreapi.data.entity.Customer;
import com.bookstore.bookstoreapi.data.repository.CustomerRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  @Autowired private CustomerRepository customerRepository;

  public Optional<Customer> listCustomers(Long customerId) {

    return customerRepository.findById(customerId);
  }
}
