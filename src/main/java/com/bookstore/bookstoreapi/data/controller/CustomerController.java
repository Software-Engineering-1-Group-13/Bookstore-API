package com.bookstore.bookstoreapi.data.controller;

import com.bookstore.bookstoreapi.data.entity.Customer;
import com.bookstore.bookstoreapi.data.service.CustomerService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

  @Autowired private CustomerService customerService;

  @GetMapping("user/{customerID}")
  public ResponseEntity<String> listCustomers(@PathVariable Long customerID) {

    Optional<Customer> listCustomers = customerService.listCustomers(customerID);
    System.out.println("List customers: " + listCustomers);
    if (listCustomers.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    Customer customer = listCustomers.get();
    return ResponseEntity.ok(customer.getPassword());
  }
}
