package ru.otus.homework;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.homework.service.QuizService;

public class Main {
    private static final String I_DONT_HAVE_QUESTIONS = "Sorry. I don't have questions.";

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuizService quizService = context.getBean(QuizService.class);
        try {
            final var quizzes = quizService.quizzes();
            quizzes.forEach(quiz -> {
                System.out.println(quiz.getName() + ":");
                quiz.getAnswers().forEach(answer -> System.out.println("  " + answer.getName()));
            });
            if (quizzes.isEmpty()){
                System.out.println(I_DONT_HAVE_QUESTIONS);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        context.close();
    }
}