package io.github.yurasava.bookservice.service;

import io.github.yurasava.bookservice.controller.RequestDispatcher;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Scanner;

@Component
public class ConsoleService {
    private final RequestDispatcher requestDispatcher;
    private final MessageSource messageSource;

    public ConsoleService(RequestDispatcher requestDispatcher, MessageSource messageSource) {
        this.requestDispatcher = requestDispatcher;
        this.messageSource = messageSource;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        Locale locale = selectedLocale(scanner);

        while (true) {
            System.out.println(messageSource.getMessage("ChooseCommand", null, locale));
            System.out.println(messageSource.getMessage("ShowBooks", null, locale));
            System.out.println(messageSource.getMessage("NewBook", null, locale));
            System.out.println(messageSource.getMessage("EditBooks", null, locale));
            System.out.println(messageSource.getMessage("DeleteBook", null, locale));
            System.out.println(messageSource.getMessage("Exit", null, locale));
            int option = scanner.nextInt();
            requestDispatcher.doDispatch(option, locale);
        }
    }

    private Locale selectedLocale(Scanner scanner) {
        System.out.println("Choose language / Выберите язык: ");
        System.out.println("1. English / Английский");
        System.out.println("2. Russian / Русский");
        int optionLanguage = scanner.nextInt();
        switch (optionLanguage) {
            case 1:
                return Locale.ENGLISH;
            case 2:
                return Locale.of("ru", "RU");
            default:
                System.out.println("Invalid choice. Defaulting to English.");
                return Locale.ENGLISH;
        }
    }
}
