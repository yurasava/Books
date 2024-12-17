package io.github.yurasava.bookservice.service;

import io.github.yurasava.bookservice.entities.Book;
import io.github.yurasava.bookservice.repository.CsvBookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final CsvBookRepository csvBookRepository;

    public BookService(CsvBookRepository csvBookRepository) {
        this.csvBookRepository = csvBookRepository;
    }

    public List<Book> getAllBooks() {
        return csvBookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return csvBookRepository.findById(id);
    }

    public void createNewBook(Book book) {
        csvBookRepository.create(book);
    }

    public void editBook(Long id, Book book) {
        csvBookRepository.update(id, book);
    }

    public void deleteBook(Long id) {
        csvBookRepository.delete(id);
    }
}
