package io.github.yurasava.bookservice.repository;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import io.github.yurasava.bookservice.entities.Book;
import io.github.yurasava.bookservice.exception.CsvParseException;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class CsvBookRepository implements BookRepository {
    private static final String CSV_FILE_PATH = "src/main/resources/books.csv";
    private final CsvMapper csvMapper = new CsvMapper();
    private final CsvSchema csvSchema = CsvSchema.builder()
            .addColumn("id")
            .addColumn("title")
            .addColumn("author")
            .addColumn("description")
            .build()
            .withHeader();

    @Override
    public List<Book> findAll() throws CsvParseException {
        return readCsvFile();
    }

    @Override
    public Optional<Book> findById(Long id) {
        try {
            List<Book> books = readCsvFile();
            return books.stream().filter(book -> Objects.equals(book.getId(), id)).findFirst();
        } catch (CsvParseException e) {
            return Optional.empty();
        }
    }

    @Override
    public void create(Book book) {
        List<Book> books = findAll();
        long maxId = books.stream()
                .mapToLong(Book::getId)
                .max().orElse(0);
        book.setId(maxId + 1);
        books.add(book);
        writeBooksToCsv(books);
    }

    @Override
    public void update(Long id, Book book) {
        List<Book> books = findAll();
        for (Book b : books) {
            if (b.getId().equals(id)) {
                b.setTitle(book.getTitle());
                b.setAuthor(book.getAuthor());
                b.setDescription(book.getDescription());
            }
        }
        writeBooksToCsv(books);
    }

    @Override
    public void delete(Long id) {
        List<Book> books = findAll();
        books.removeIf(book -> Objects.equals(book.getId(), id));
        writeBooksToCsv(books);
    }

    public List<Book> readCsvFile() {
        try {
            File file = new File(CSV_FILE_PATH);
            MappingIterator<Book> iterator = csvMapper.readerFor(Book.class).with(csvSchema).readValues(file);
            return iterator.readAll();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void writeBooksToCsv(List<Book> books) throws CsvParseException {
        try {
            csvMapper.writerFor(Book.class).with(csvSchema).writeValues(new File(CSV_FILE_PATH)).writeAll(books);
        } catch (IOException e) {
            throw new CsvParseException("Failed to write to CSV file", e);
        }
    }
}
