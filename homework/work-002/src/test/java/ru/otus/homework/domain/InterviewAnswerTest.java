package ru.otus.homework.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.otus.homework.DataFactory.INTERVIEW_ANSWER;

@DisplayName("Ответ опроса")
class InterviewAnswerTest {

    @DisplayName("возвращаются корректный ответ")
    @Test
    void shouldReturnCorrectAnswer() {
        InterviewAnswer interviewAnswer = new InterviewAnswer(INTERVIEW_ANSWER);
        assertThat(interviewAnswer.getAnswer())
                .isEqualTo(INTERVIEW_ANSWER);
    }
}