package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.domain.Interview;
import ru.otus.homework.domain.Personality;
import ru.otus.homework.domain.Quiz;
import ru.otus.homework.exception.EmptyDataQuizException;
import ru.otus.homework.exception.EmptyFileNameQuizException;
import ru.otus.homework.exception.FileNotFoundQuizException;
import ru.otus.homework.exception.IOQuizException;
import ru.otus.homework.exception.LineValidationQuizException;
import ru.otus.homework.service.IOService;
import ru.otus.homework.service.IdentityService;
import ru.otus.homework.service.InterviewResultService;
import ru.otus.homework.service.PrintService;
import ru.otus.homework.service.QuizRunnerService;
import ru.otus.homework.service.QuizService;
import ru.otus.homework.util.QuizUtil;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QuizRunnerServiceImpl implements QuizRunnerService {
    private final QuizService quizService;
    private final IOService ioService;
    private final IdentityService identityService;
    private final InterviewResultService interviewResultService;
    private final PrintService printService;
    private final QuizUtil quizUtil;

    @Override
    public void run() {
        try {
            final List<Quiz> quizzes = quizService.getQuizzes();

            if (quizzes.isEmpty()) {
                throw new EmptyDataQuizException("Quiz data is empty");
            }

            printService.outputMessage("\n");
            final Personality personality = personality();
            final Interview interview = quizUtil.getInterview(personality);
            quizzes.forEach(quiz -> quizUtil.quizInterview(quiz, interview));
            interviewResultService.printStatistic(interview);

        } catch (EmptyFileNameQuizException | FileNotFoundQuizException e) {
            // TODO add to app log
            printService.outputLocalizedMessage("error.message.application.configuration.error");
        } catch (LineValidationQuizException e) {
            // TODO add to app log
            printService.outputLocalizedMessage("error.message.invalid.data.format");
        } catch (IOQuizException e) {
            // TODO add to app log
            printService.outputLocalizedMessage("error.message.error.reading.data");
        } catch (EmptyDataQuizException e) {
            // TODO add to app log
            printService.outputLocalizedMessage("error.message.i.dont.have.questions");
        } catch (Exception e) {
            // TODO add to app log
            printService.outputLocalizedMessage("error.message.for.unhandled.exception");
        }
    }

    private Personality personality() {
        printService.outputMessage("\n");
        printService.outputLocalizedMessage("identify.yourself");
        String name = identityService.askName();
        String surname = identityService.askSurname();
        return new Personality(name, surname);
    }
}