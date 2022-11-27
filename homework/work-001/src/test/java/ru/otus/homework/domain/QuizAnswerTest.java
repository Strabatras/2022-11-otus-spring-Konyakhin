package ru.otus.homework.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuizAnswerTest {

    @Test
    void getNameShouldBeCorrect() {
        final String quizAnswerName = "QuizAnswerName";
        QuizAnswer quizAnswer = new QuizAnswer(quizAnswerName);
        assertEquals(quizAnswerName, quizAnswer.getName());
    }
}