package com.bookstore.bookstoreapi.data.repository;

import com.bookstore.bookstoreapi.data.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {}
