package ru.otus.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.homework.properties.ShellPropertie;
import ru.otus.homework.service.QuizRunnerService;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class);
        ShellPropertie shellPropertie = context.getBean(ShellPropertie.class);
        if (!shellPropertie.getInteractive().isEnabled()) {
            QuizRunnerService quizRunnerService = context.getBean(QuizRunnerService.class);
            quizRunnerService.run();
        }
    }
}