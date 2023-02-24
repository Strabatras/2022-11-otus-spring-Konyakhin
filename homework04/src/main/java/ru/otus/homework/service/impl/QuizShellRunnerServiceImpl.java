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
import ru.otus.homework.service.InterviewResultService;
import ru.otus.homework.service.LocalizationService;
import ru.otus.homework.service.PrintService;
import ru.otus.homework.service.QuizService;
import ru.otus.homework.service.QuizShellRunnerService;
import ru.otus.homework.util.QuizUtil;

import java.util.List;
import java.util.StringJoiner;

import static org.apache.commons.lang3.ObjectUtils.isEmpty;

@RequiredArgsConstructor
@Service
public class QuizShellRunnerServiceImpl implements QuizShellRunnerService {
    private final QuizService quizService;
    private final InterviewResultService interviewResultService;
    private final LocalizationService localizationService;
    private final PrintService printService;
    private final QuizUtil quizUtil;

    private Personality personality;
    private Interview interview;

    @Override
    public void authorizationRun(String name, String surname) {
        StringJoiner outputMessage = new StringJoiner(". ");
        if (isEmpty(name)) {
            outputMessage.add(localizationService.getMessage("identify.name.cant.be.empty"));
        }
        if (isEmpty(surname)) {
            outputMessage.add(localizationService.getMessage("identify.surname.cant.be.empty"));
        }
        if (outputMessage.length() == 0) {
            personality = new Personality(name, surname);
            outputMessage.add(localizationService.getMessage("identify.you.can.answer.questions"));
        }
        printService.outputMessage(outputMessage.toString());
    }

    @Override
    public void quizRun(){
        if (personality == null){
            printService.outputLocalizedMessage("identify.authorized.please");
            return;
        }
        try {
            final List<Quiz> quizzes = quizService.getQuizzes();

            if (quizzes.isEmpty()) {
                throw new EmptyDataQuizException("Quiz data is empty");
            }

            printService.outputMessage("\n");
            interview = quizUtil.getInterview(personality);
            quizzes.forEach(quiz -> quizUtil.quizInterview(quiz, interview));
            printService.outputLocalizedMessage("have.access.to.questions.statistics");
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

    @Override
    public void statisticRun(){
        if (interview == null){
            printService.outputLocalizedMessage("please.answer.the.questions");
            return;
        }
        interviewResultService.printStatistic(interview);
        printService.outputLocalizedMessage("enter.exit.to.complete");
    }
}