package io.github.yurasava.bookservice.controller;

import io.github.yurasava.bookservice.entities.Book;
import io.github.yurasava.bookservice.service.BookService;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Scanner;

@Controller
public class RequestDispatcher {
    private final BookService bookService;

    public RequestDispatcher(BookService bookService) {
        this.bookService = bookService;
    }

    public void doDispatch(int userInput) {

        List<Book> books = bookService.getAllBooks();
        Scanner scanner = new Scanner(System.in);
        Book book = new Book();

        switch (userInput) {
            case 1:
                books.forEach(System.out::println);
                break;
            case 2:
                addBook(scanner, book);
                break;
            case 3:
                editBook(scanner, book);
                break;
            case 4:
                deleteBook(scanner);
                break;
            case 0:
                System.out.println("До свидания!");
                System.exit(0);
            default:
                System.out.println("Неверная комманда!");
                break;
        }
    }

    private void addBook(Scanner scanner, Book book) {
        System.out.println("Введите название книги: ");
        book.setTitle(scanner.nextLine());
        System.out.println("Введите автора книги: ");
        book.setAuthor(scanner.nextLine());
        System.out.println("Введите описание книги: ");
        book.setDescription(scanner.nextLine());
        bookService.createNewBook(book);
        System.out.println("Книга успешно добавлена!");
    }

    private void editBook(Scanner scanner, Book book) {
        System.out.println("Введите id книги: ");
        long id = scanner.nextLong();
        if (bookService.getBookById(id).isEmpty()) {
            System.out.println("Книга с таким id не найдена!");
            return;
        }
        System.out.println("Введите название книги: ");
        book.setTitle(scanner.nextLine());
        System.out.println("Введите автора книги: ");
        book.setAuthor(scanner.nextLine());
        System.out.println("Введите описание книги: ");
        book.setDescription(scanner.nextLine());
        bookService.editBook(id, book);
        System.out.println("Книга успешно отредактирована!");
    }

    private void deleteBook(Scanner scanner) {
        System.out.println("Введите id книги для удаления: ");
        long id = scanner.nextLong();
        if (bookService.getBookById(id).isEmpty()) {
            System.out.println("Книга с таким id не найдена!");
            return;
        }
        bookService.deleteBook(id);
        System.out.println("Книга успешно удалена!");
    }
}
