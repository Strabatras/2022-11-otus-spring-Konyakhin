package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.domain.Interview;
import ru.otus.homework.domain.InterviewQuestionAnswer;
import ru.otus.homework.domain.Personality;
import ru.otus.homework.domain.Quiz;
import ru.otus.homework.domain.QuizAnswer;
import ru.otus.homework.exception.EmptyDataQuizException;
import ru.otus.homework.exception.EmptyFileNameQuizException;
import ru.otus.homework.exception.FileNotFoundQuizException;
import ru.otus.homework.exception.IOQuizException;
import ru.otus.homework.exception.LineValidationQuizException;
import ru.otus.homework.service.IOService;
import ru.otus.homework.service.IdentityService;
import ru.otus.homework.service.InterviewResultService;
import ru.otus.homework.service.QuizRunnerService;
import ru.otus.homework.service.QuizService;
import ru.otus.homework.util.Localization;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@RequiredArgsConstructor
@Service
public class QuizRunnerServiceImpl implements QuizRunnerService {
    private final QuizService quizService;
    private final IOService ioService;
    private final IdentityService identityService;
    private final InterviewResultService interviewResultService;
    private final Localization localization;

    @Override
    public void run() {

        try {
            final List<Quiz> quizzes = quizService.getQuizzes();

            if (quizzes.isEmpty()) {
                throw new EmptyDataQuizException("Quiz data is empty");
            }

            final Personality personality = personality();
            ioService.outputString("\n");
            final Interview interview = interview(personality);

            quizzes.forEach(quiz -> quizInterviewRun(quiz, interview));

            interviewResultService.printStatistic(interview);

        } catch (EmptyFileNameQuizException | FileNotFoundQuizException e) {
            // TODO add to app log
            ioService.outputString(localization.getMessage("error.message.application.configuration.error"));
        } catch (LineValidationQuizException e) {
            // TODO add to app log
            ioService.outputString(localization.getMessage("error.message.invalid.data.format"));
        } catch (IOQuizException e) {
            // TODO add to app log
            ioService.outputString(localization.getMessage("error.message.error.reading.data"));
        } catch (EmptyDataQuizException e) {
            // TODO add to app log
            ioService.outputString(localization.getMessage("error.message.i.dont.have.questions"));
        } catch (Exception e) {
            // TODO add to app log
            ioService.outputString(localization.getMessage("error.message.for.unhandled.exception"));
        }
    }

    private void outputQuizAnswer(QuizAnswer quizAnswer) {
        if (isNotEmpty(quizAnswer.getName()) && isNotBlank(quizAnswer.getName())) {
            ioService.outputString("  -" + quizAnswer.getName());
        }
    }

    private InterviewQuestionAnswer interviewQuestionAnswer(Quiz quiz) {
        final String readString = ioService.readString();
        return new InterviewQuestionAnswer(quiz, readString);
    }

    private void quizInterviewRun(Quiz quiz, Interview interview) {
        ioService.outputString(quiz.getName() + ":");
        quiz.getAnswers().forEach(this::outputQuizAnswer);
        final InterviewQuestionAnswer interviewQuestionAnswer = interviewQuestionAnswer(quiz);
        interview.setQuestionAnswer(interviewQuestionAnswer);
    }

    private Personality personality() {
        ioService.outputString("\n" + localization.getMessage("identify.yourself"));
        String name = identityService.askName();
        String surname = identityService.askSurname();
        return new Personality(name, surname);
    }

    private Interview interview(Personality personality) {
        return new Interview(personality, new ArrayList<>());
    }
}