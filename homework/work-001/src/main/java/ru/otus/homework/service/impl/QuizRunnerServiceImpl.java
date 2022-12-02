package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import ru.otus.homework.service.IOService;
import ru.otus.homework.service.QuizRunnerService;
import ru.otus.homework.service.QuizService;

@RequiredArgsConstructor
public class QuizRunnerServiceImpl implements QuizRunnerService {
    private final QuizService quizService;
    private final IOService ioService;

    private static final String I_DONT_HAVE_QUESTIONS = "Sorry. I don't have questions.";

    @Override
    public void run() {
        try {
            final var quizzes = quizService.quizzes();
            quizzes.forEach(quiz -> {
                ioService.outputString(quiz.getName() + ":");
                quiz.getAnswers().forEach(answer -> System.out.println("  " + answer.getName()));
            });
            if (quizzes.isEmpty()) {
                ioService.outputString(I_DONT_HAVE_QUESTIONS);
            }
        } catch (Exception e) {
            ioService.outputString(e.getMessage());
        }
    }
}