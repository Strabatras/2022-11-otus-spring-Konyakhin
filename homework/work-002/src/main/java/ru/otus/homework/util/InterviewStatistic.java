package ru.otus.homework.util;

import ru.otus.homework.domain.Quiz;

public class InterviewStatistic {
    public static long getQuizCorrectAnswerCount(Quiz quiz, String interviewAnswer){
        return quiz.getCorrectAnswers()
                .stream()
                .filter(quizAnswer -> interviewAnswer.equalsIgnoreCase(quizAnswer.getName()))
                .count();
    }

    public static boolean hasQuizCorrectAnswer(Quiz quiz, String interviewAnswer){
        return getQuizCorrectAnswerCount(quiz, interviewAnswer) > 0;
    }
}