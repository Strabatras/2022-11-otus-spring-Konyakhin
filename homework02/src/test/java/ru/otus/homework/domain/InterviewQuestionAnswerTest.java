package ru.otus.homework.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.otus.homework.DataFactory.INTERVIEW_ANSWER;
import static ru.otus.homework.DataFactory.correctQuizWithAnswers;

@DisplayName("Опрос вопросы/ответ")
class InterviewQuestionAnswerTest {

    private Quiz quiz;

    @BeforeEach
    void SetUp() {
        quiz = correctQuizWithAnswers();
    }

    @DisplayName("возвращается корректный вопрос со списком правильных и вариантов ответов")
    @Test
    void shouldReturnCorrectQuiz() {
        InterviewQuestionAnswer interviewQuestionAnswer = new InterviewQuestionAnswer(quiz, INTERVIEW_ANSWER);
        assertThat(interviewQuestionAnswer.getQuiz())
                .extracting(Quiz::getName,
                        toQuizAnswers -> toQuizAnswers.getAnswers().stream().map(QuizAnswer::getName).collect(Collectors.toList()),
                        toQuizCorrectAnswers -> toQuizCorrectAnswers.getAnswers().stream()
                                .map(QuizAnswer::getCorrectAnswer)
                                .filter(Objects::nonNull)
                                .collect(Collectors.toList())
                )
                .containsExactly("Q", Arrays.asList("Q1", "Q2", "Q3", "Q4", "Q5", "Q6", "Q7"), Arrays.asList("Q2", "Q4", "Q6"));
    }


    @DisplayName("возвращается корректный текст ответа опроса")
    @Test
    void shouldReturnCorrectInterviewAnswer() {
        InterviewQuestionAnswer interviewQuestionAnswer = new InterviewQuestionAnswer(quiz, INTERVIEW_ANSWER);
        assertThat(interviewQuestionAnswer.getInterviewAnswer())
                .isEqualTo(INTERVIEW_ANSWER);
    }
}