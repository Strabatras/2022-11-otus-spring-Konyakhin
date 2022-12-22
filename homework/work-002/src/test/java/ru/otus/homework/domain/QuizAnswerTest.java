package ru.otus.homework.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.otus.homework.DataFactory.QUIZ_ANSWER_NAME;

@DisplayName("Ответ на вопрос")
class QuizAnswerTest {

    @DisplayName("корректно возвращается название ответа")
    @Test
    void shouldReturnCorrectName() {
        QuizAnswer quizAnswer = new QuizAnswer(QUIZ_ANSWER_NAME);
        assertEquals(QUIZ_ANSWER_NAME, quizAnswer.getName());
    }
}