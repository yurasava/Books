package io.github.yurasava.bookservice;

import io.github.yurasava.bookservice.config.AppConfig;
import io.github.yurasava.bookservice.service.ConsoleService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ConsoleService consoleService = context.getBean(ConsoleService.class);
        consoleService.start();
    }
}
