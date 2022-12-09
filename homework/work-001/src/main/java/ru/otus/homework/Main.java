package ru.otus.homework;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.homework.service.QuizRunnerService;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuizRunnerService quizRunnerService = context.getBean(QuizRunnerService.class);
        quizRunnerService.run();
        context.close();
    }
}