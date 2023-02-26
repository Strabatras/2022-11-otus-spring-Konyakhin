package ru.otus.homework.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.otus.homework.DataFactory.QUIZ_ANSWER_NAME;

@DisplayName("Ответ на вопрос")
@SpringBootTest(properties = "spring.shell.interactive.enabled=false")
class QuizInterviewAnswerTest {

    @DisplayName("корректно возвращается название ответа")
    @Test
    void shouldReturnCorrectName() {
        QuizAnswer quizAnswer = new QuizAnswer(QUIZ_ANSWER_NAME);
        assertEquals(QUIZ_ANSWER_NAME, quizAnswer.getName());
    }
}