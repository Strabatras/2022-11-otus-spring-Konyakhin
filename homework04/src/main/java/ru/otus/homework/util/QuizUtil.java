package ru.otus.homework.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.homework.domain.Interview;
import ru.otus.homework.domain.InterviewQuestionAnswer;
import ru.otus.homework.domain.Personality;
import ru.otus.homework.domain.Quiz;
import ru.otus.homework.domain.QuizAnswer;
import ru.otus.homework.service.IOService;
import ru.otus.homework.service.PrintService;

import java.util.ArrayList;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@RequiredArgsConstructor
@Component
public class QuizUtil {
    private final PrintService printService;
    private final IOService ioService;

    public Interview getInterview(Personality personality) {
        return new Interview(personality, new ArrayList<>());
    }

    public void quizInterview(Quiz quiz, Interview interview) {
        printService.outputMessage(quiz.getName() + ":");
        quiz.getAnswers().forEach(this::outputQuizAnswer);
        final InterviewQuestionAnswer interviewQuestionAnswer = interviewQuestionAnswer(quiz);
        interview.setQuestionAnswer(interviewQuestionAnswer);
    }

    private void outputQuizAnswer(QuizAnswer quizAnswer) {
        if (isNotEmpty(quizAnswer.getName()) && isNotBlank(quizAnswer.getName())) {
            printService.outputMessage("  -" + quizAnswer.getName());
        }
    }

    private InterviewQuestionAnswer interviewQuestionAnswer(Quiz quiz) {
        final String readString = ioService.readString();
        return new InterviewQuestionAnswer(quiz, readString);
    }
}