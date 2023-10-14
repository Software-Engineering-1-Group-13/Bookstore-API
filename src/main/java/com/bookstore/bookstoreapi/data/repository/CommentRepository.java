package com.bookstore.bookstoreapi.data.repository;

import com.bookstore.bookstoreapi.data.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {}
