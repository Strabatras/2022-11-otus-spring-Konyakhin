package ru.otus.homework.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static ru.otus.homework.DataFactory.QUIZ_NAME;
import static ru.otus.homework.DataFactory.answers;

@DisplayName("Вопрос")
class QuizTest {
    private static List<QuizAnswer> answers;

    @BeforeEach
    void SetUp() {
        answers = answers("QuizAnswer #", 4);
    }

    @DisplayName("должен быть корректный конструктор")
    @Test
    void shouldBeCorrectConstructor() {
        Quiz quiz = new Quiz(QUIZ_NAME, answers);
        assertEquals(QUIZ_NAME, quiz.getName());
        assertNotNull(quiz.getAnswers());
        assertEquals(answers, quiz.getAnswers());
    }

    @DisplayName("корректно возвращается название вопроса")
    @Test
    void shouldReturnCorrectName() {
        Quiz quiz = new Quiz(QUIZ_NAME, answers);
        assertEquals(QUIZ_NAME, quiz.getName());
    }

    @DisplayName("корректно возвращается список ответов")
    @Test
    void shouldReturnCorrectAnswersList() {
        Quiz quiz = new Quiz(QUIZ_NAME, answers);
        assertNotNull(quiz.getAnswers());
        assertEquals(answers, quiz.getAnswers());
    }
}