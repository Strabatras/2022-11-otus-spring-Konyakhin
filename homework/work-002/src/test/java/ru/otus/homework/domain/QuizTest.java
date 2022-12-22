package ru.otus.homework.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static ru.otus.homework.DataFactory.QUIZ_NAME;
import static ru.otus.homework.DataFactory.answers;
import static ru.otus.homework.DataFactory.correctAnswers;

@DisplayName("Вопрос")
class QuizTest {
    private static List<QuizAnswer> answers;
    private static List<QuizAnswer> correctAnswers;

    @BeforeEach
    void SetUp() {
        answers = answers("QuizAnswer #", 4);
        correctAnswers = correctAnswers("QuizAnswer #", 1,3);
    }

    @DisplayName("должен быть корректный конструктор")
    @Test
    void shouldBeCorrectConstructor() {
        Quiz quiz = new Quiz(QUIZ_NAME, answers, correctAnswers);
        assertEquals(QUIZ_NAME, quiz.getName());
        assertEquals(answers, quiz.getAnswers());
        assertEquals(correctAnswers, quiz.getCorrectAnswers());
    }

    @DisplayName("корректно возвращается название вопроса")
    @Test
    void shouldReturnCorrectName() {
        Quiz quiz = new Quiz(QUIZ_NAME, answers, correctAnswers);
        assertEquals(QUIZ_NAME, quiz.getName());
    }

    @DisplayName("корректно возвращается список ответов")
    @Test
    void shouldCorrectlyReturnAnswersList() {
        Quiz quiz = new Quiz(QUIZ_NAME, answers, correctAnswers);
        assertNotNull(quiz.getAnswers());
        assertEquals(answers, quiz.getAnswers());
    }

    @DisplayName("корректно возвращается список правильных ответов")
    @Test
    void shouldCorrectlyReturnCorrectAnswersList() {
        Quiz quiz = new Quiz(QUIZ_NAME, answers, correctAnswers);
        assertNotNull(quiz.getAnswers());
        assertEquals(answers, quiz.getAnswers());
    }
}