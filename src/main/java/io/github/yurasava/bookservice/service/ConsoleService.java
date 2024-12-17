package io.github.yurasava.bookservice.service;

import io.github.yurasava.bookservice.controller.RequestDispatcher;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleService {
    private final RequestDispatcher requestDispatcher;

    public ConsoleService(RequestDispatcher requestDispatcher) {
        this.requestDispatcher = requestDispatcher;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Выберите команду: ");
            System.out.println("1. Вывести список книг");
            System.out.println("2. Создать новую книгу");
            System.out.println("3. Редактировать книгу");
            System.out.println("4. Удалить книгу");
            System.out.println("0. Выход из приложения");
            int option = scanner.nextInt();
            requestDispatcher.doDispatch(option);
        }
    }
}
