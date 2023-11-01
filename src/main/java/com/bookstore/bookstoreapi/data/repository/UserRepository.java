package com.bookstore.bookstoreapi.data.repository;

import com.bookstore.bookstoreapi.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {}
