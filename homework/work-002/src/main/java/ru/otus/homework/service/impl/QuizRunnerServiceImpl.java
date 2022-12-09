package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.enumeration.NoticeEnum;
import ru.otus.homework.exception.FileNotFoundQuizException;
import ru.otus.homework.exception.FileReadLineQuizException;
import ru.otus.homework.exception.IOQuizException;
import ru.otus.homework.service.IOService;
import ru.otus.homework.service.QuizRunnerService;
import ru.otus.homework.service.QuizService;

@RequiredArgsConstructor
@Service
public class QuizRunnerServiceImpl implements QuizRunnerService {
    private final QuizService quizService;
    private final IOService ioService;

    @Override
    public void run() {
        try {
            final var quizzes = quizService.getQuizzes();
            quizzes.forEach(quiz -> {
                ioService.outputString(quiz.getName() + ":");
                quiz.getAnswers().forEach(answer -> ioService.outputString("  " + answer.getName()));
            });
            if (quizzes.isEmpty()) {
                ioService.outputString(NoticeEnum.I_DONT_HAVE_QUESTIONS.getNotice());
            }
        } catch (FileNotFoundQuizException e) {
            ioService.outputString(NoticeEnum.QUIZ_CSV_FILE_NOT_FOUND.getNotice());
        } catch (IOQuizException e) {
            ioService.outputString(NoticeEnum.QUIZ_CSV_FILE_PREPARATION_ERROR.getNotice());
        } catch (FileReadLineQuizException e) {
            ioService.outputString(NoticeEnum.QUIZ_CSV_FILE_READING_ERROR.getNotice());
        } catch (Exception e) {
            ioService.outputString(NoticeEnum.UNHANDLED_EXCEPTION.getNotice());
        }
    }
}