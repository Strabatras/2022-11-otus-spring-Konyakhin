package ru.otus.homework.util;

import ru.otus.homework.domain.Quiz;

public class InterviewStatistic {
    public static boolean hasQuizCorrectAnswer(Quiz quiz) {
        return quiz.getAnswers().stream()
                .anyMatch(quizAnswer -> quizAnswer.getCorrectAnswer() != null);
    }

    public static boolean isQuizCorrectAnswer(Quiz quiz, String interviewAnswer) {
        return quiz.getAnswers()
                .stream()
                .anyMatch(quizAnswer -> interviewAnswer.equalsIgnoreCase(quizAnswer.getCorrectAnswer()));
    }
}