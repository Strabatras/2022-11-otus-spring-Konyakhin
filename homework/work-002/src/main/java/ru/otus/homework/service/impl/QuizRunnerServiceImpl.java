package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.domain.Interview;
import ru.otus.homework.domain.Personality;
import ru.otus.homework.domain.Quiz;
import ru.otus.homework.exception.EmptyDataQuizException;
import ru.otus.homework.exception.IOQuizException;
import ru.otus.homework.exception.IllegalArgumentQuizException;
import ru.otus.homework.exception.LineValidationQuizException;
import ru.otus.homework.service.IOService;
import ru.otus.homework.service.IdentityService;
import ru.otus.homework.service.InterviewAnswerService;
import ru.otus.homework.service.InterviewQuestionAnswerService;
import ru.otus.homework.service.InterviewResultService;
import ru.otus.homework.service.InterviewService;
import ru.otus.homework.service.PersonalityService;
import ru.otus.homework.service.QuizRunnerService;
import ru.otus.homework.service.QuizService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QuizRunnerServiceImpl implements QuizRunnerService {
    private final QuizService quizService;
    private final IOService ioService;
    private final InterviewService interviewService;
    private final InterviewQuestionAnswerService interviewQuestionAnswerService;
    private final InterviewAnswerService interviewAnswerService;
    private final IdentityService identityService;
    private final PersonalityService personalityService;
    private final InterviewResultService interviewResultService;

    private static final String MESSAGE_I_DONT_HAVE_QUESTIONS = "Sorry. I don't have questions.";
    private static final String MESSAGE_APPLICATION_CONFIGURATION_ERROR = "Sorry. Application configuration error.";
    private static final String MESSAGE_INVALID_DATA_FORMAT = "Sorry. Invalid data format with questions.";
    private static final String MESSAGE_ERROR_READING_DATA = "Sorry. Error reading data with questions.";
    private static final String MESSAGE_FOR_UNHANDLED_EXCEPTION = "Sorry. Something went wrong.";

    private Personality personality(){
        ioService.outputString("Identify yourself please.");
        String name = identityService.askName();
        String surname = identityService.askSurname();
        return personalityService.createPersonality(name, surname);
    }

    @Override
    public void run() {

        try {
            final List<Quiz> quizzes = quizService.getQuizzes();

            if (quizzes.isEmpty()) {
                throw new EmptyDataQuizException("Quiz data is empty");
            }

            final Personality personality = personality();
            ioService.outputString("\n");
            final Interview interview = interviewService.createInterview(personality);

            quizzes.forEach(quiz -> {
                ioService.outputString(quiz.getName() + ":");
                quiz.getAnswers().forEach(answer -> ioService.outputString("  -" + answer.getName()));
                String interviewAnswer = ioService.readString();
                interview.setQuestionAnswer(
                        interviewQuestionAnswerService
                                .createInterviewQuestionAnswer(quiz, interviewAnswerService.createInterviewAnswer(interviewAnswer))
                );
            });

            interviewResultService.printStatistic(interview);

        } catch (IllegalArgumentQuizException e) {
            // TODO add to app log
            ioService.outputString(MESSAGE_APPLICATION_CONFIGURATION_ERROR);
        } catch (LineValidationQuizException e) {
            // TODO add to app log
            ioService.outputString(MESSAGE_INVALID_DATA_FORMAT);
        } catch (IOQuizException e){
            // TODO add to app log
            ioService.outputString(MESSAGE_ERROR_READING_DATA);
        } catch (EmptyDataQuizException e){
            // TODO add to app log
            ioService.outputString(MESSAGE_I_DONT_HAVE_QUESTIONS);
        } catch (Exception e){
            // TODO add to app log
            ioService.outputString(MESSAGE_FOR_UNHANDLED_EXCEPTION);
        }
    }
}