package ru.otus.homework.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuizAnswerTest {
    private static final String QUIZ_ANSWER_NAME = "QuizAnswerName";

    @Test
    void getNameShouldBeCorrect() {
        QuizAnswer quizAnswer = new QuizAnswer(QUIZ_ANSWER_NAME, false);
        assertEquals(QUIZ_ANSWER_NAME, quizAnswer.getName());
    }

    @Test
    void isCorrectShouldBeCorrect() {
        QuizAnswer quizAnswer = new QuizAnswer(QUIZ_ANSWER_NAME, true);
        assertTrue(quizAnswer.isCorrect());
    }
}