package io.github.yurasava.bookservice.repository;

import io.github.yurasava.bookservice.entities.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    List<Book> findAll();
    Optional<Book> findById(Long id);
    void create(Book book);
    void update(Long id, Book book);
    void delete(Long id);
}
