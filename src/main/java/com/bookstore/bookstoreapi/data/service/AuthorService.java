package com.bookstore.bookstoreapi.data.service;

import com.bookstore.bookstoreapi.data.entity.Author;
import com.bookstore.bookstoreapi.data.repository.AuthorRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

  @Autowired private AuthorRepository authorRepository;

  public Optional<Author> createAuthor(Author author) {

    Author createdAuthor = authorRepository.save(author);

    return Optional.of(createdAuthor);
  }
}
