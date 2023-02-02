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
import ru.otus.homework.properties.ShellPropertie;
import ru.otus.homework.service.IOService;
import ru.otus.homework.service.IdentityService;
import ru.otus.homework.service.InterviewResultService;
import ru.otus.homework.service.PrintService;
import ru.otus.homework.service.QuizRunnerService;
import ru.otus.homework.service.QuizService;
import ru.otus.homework.util.ShellQuizRunner;

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
    private final PrintService printService;
    private final ShellPropertie shellPropertie;

    @Override
    public void run() {
        if (!shellPropertie.getInteractive().isEnabled()) {
            final Interview interview = run(personality());
            interviewResultService.printStatistic(interview);
        }
    }

    public void runInShell(ShellQuizRunner shellQuizRunner){
        final Interview interview = run(shellQuizRunner.getPersonality());
        shellQuizRunner.setInterview(interview);
    }

    public void runOutputStatisticInShell(ShellQuizRunner shellQuizRunner){
        final Interview interview = shellQuizRunner.getInterview();
        interviewResultService.printStatistic(interview);
    }

    private Interview run(Personality personality){
        Interview interview = null;
        try {
            interview = runQuiz(personality);
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
        return interview;
    }

    private Interview runQuiz(Personality personality){
        final List<Quiz> quizzes = quizService.getQuizzes();

        if (quizzes.isEmpty()) {
            throw new EmptyDataQuizException("Quiz data is empty");
        }

        ioService.outputString("\n");

        final Interview interview = interview(personality);
        quizzes.forEach(quiz -> quizInterviewRun(quiz, interview));
        return interview;
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
        ioService.outputString("\n");
        printService.outputLocalizedMessage("identify.yourself");
        String name = identityService.askName();
        String surname = identityService.askSurname();
        return new Personality(name, surname);
    }

    private Interview interview(Personality personality) {
        return new Interview(personality, new ArrayList<>());
    }
}