package ru.otus.homework.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class QuizTest {
    private static final String QUIZ_NAME = "QuizName";
    private static List<QuizAnswer> answers;

    @BeforeEach
    void SetUp() {
        answers = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            answers.add(new QuizAnswer("QuizAnswer #" + i));
        }
    }

    @Test
    void constructorShouldBeCorrect() {
        Quiz quiz = new Quiz(QUIZ_NAME, answers);
        assertEquals(QUIZ_NAME, quiz.getName());
        assertNotNull(quiz.getAnswers());
        assertEquals(answers, quiz.getAnswers());
    }

    @Test
    void getNameShouldBeCorrect() {
        Quiz quiz = new Quiz(QUIZ_NAME, answers);
        assertEquals(QUIZ_NAME, quiz.getName());
    }

    @Test
    void getAnswersShouldBeCorrect() {
        Quiz quiz = new Quiz(QUIZ_NAME, answers);
        assertNotNull(quiz.getAnswers());
        assertEquals(answers, quiz.getAnswers());
    }
}