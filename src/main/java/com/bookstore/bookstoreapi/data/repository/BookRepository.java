package com.bookstore.bookstoreapi.data.repository;

import com.bookstore.bookstoreapi.data.entity.Book;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

  List<Book> findByIsbn(String isbn);

  List<Book> findByAuthorId(Long AuthorId);

  Optional<List<Book>> findByGenre(String genre);
}
