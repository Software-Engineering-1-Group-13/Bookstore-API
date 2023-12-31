package com.bookstore.bookstoreapi.data.repository;

import com.bookstore.bookstoreapi.data.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {}
