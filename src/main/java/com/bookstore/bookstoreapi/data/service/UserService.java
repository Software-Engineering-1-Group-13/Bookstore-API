package com.bookstore.bookstoreapi.data.service;

import com.bookstore.bookstoreapi.data.dto.UserUpdateDto;
import com.bookstore.bookstoreapi.data.entity.User;
import com.bookstore.bookstoreapi.data.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired UserRepository userRepository;

  public void createUser(User user) {

    userRepository.save(user);
  }

  public Optional<User> getUserDetailsByUsername(String username) {

    return userRepository.findByUsername(username);
  }

  public void updateUser(String username, UserUpdateDto userUpdateDto) {

    User user =
        userRepository
            .findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    if (userUpdateDto.getPassword() != null) {
      user.setPassword(userUpdateDto.getPassword());
    }
    if (userUpdateDto.getName() != null) {
      user.setName(userUpdateDto.getName());
    }
    if (userUpdateDto.getHomeAddress() != null) {
      user.setHomeAddress(userUpdateDto.getHomeAddress());
    }
    userRepository.save(user);
  }
}
