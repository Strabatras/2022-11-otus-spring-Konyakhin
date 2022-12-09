package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.service.IOService;
import ru.otus.homework.service.QuizRunnerService;
import ru.otus.homework.service.QuizService;

@RequiredArgsConstructor
@Service
public class QuizRunnerServiceImpl implements QuizRunnerService {
    private final QuizService quizService;
    private final IOService ioService;

    private static final String MESSAGE_I_DONT_HAVE_QUESTIONS = "Sorry. I don't have questions.";
    private static final String MESSAGE_FOR_UNHANDLED_EXCEPTION = "Sorry. Something went wrong.";

    @Override
    public void run() {
        try {
            final var quizzes = quizService.getQuizzes();
            quizzes.forEach(quiz -> {
                ioService.outputString(quiz.getName() + ":");
                quiz.getAnswers().forEach(answer -> ioService.outputString("  " + answer.getName()));
            });
            if (quizzes.isEmpty()) {
                ioService.outputString(MESSAGE_I_DONT_HAVE_QUESTIONS);
            }
        } catch (RuntimeException e) {
            ioService.outputString(e.getMessage());
        } catch (Exception e) {
            ioService.outputString(MESSAGE_FOR_UNHANDLED_EXCEPTION);
        }
    }
}