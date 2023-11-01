package com.bookstore.bookstoreapi.data.service;

import com.bookstore.bookstoreapi.data.entity.User;
import com.bookstore.bookstoreapi.data.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired UserRepository userRepository;

  public void createUser(User user) {

    userRepository.save(user);
  }

  public Optional<List<User>> getUserDetailsByUsername(String username) {

    List<User> getUserDetailsByUser = userRepository.findByUsername(username);
    if (getUserDetailsByUser.isEmpty()) {
      return Optional.empty();
    }

    return Optional.of(getUserDetailsByUser);
  }
}
