package com.bookstore.bookstoreapi.data.repository;

import com.bookstore.bookstoreapi.data.entity.Book;
import com.bookstore.bookstoreapi.data.entity.Rating;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

  List<Rating> findByBook(Book book);
}
