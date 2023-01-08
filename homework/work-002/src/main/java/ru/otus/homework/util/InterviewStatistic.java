package ru.otus.homework.util;

import org.springframework.stereotype.Component;
import ru.otus.homework.domain.Quiz;

@Component
public class InterviewStatistic {
    public boolean hasQuizCorrectAnswer(Quiz quiz) {
        return quiz.getAnswers().stream()
                .anyMatch(quizAnswer -> quizAnswer.getCorrectAnswer() != null);
    }

    public boolean isQuizCorrectAnswer(Quiz quiz, String interviewAnswer) {
        return quiz.getAnswers()
                .stream()
                .anyMatch(quizAnswer -> interviewAnswer.equalsIgnoreCase(quizAnswer.getCorrectAnswer()));
    }
}