package ru.otus.homework.dao.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.homework.domain.InterviewAnswer;
import ru.otus.homework.domain.InterviewQuestionAnswer;
import ru.otus.homework.domain.Quiz;
import ru.otus.homework.domain.QuizAnswer;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.otus.homework.DataFactory.INTERVIEW_ANSWER;
import static ru.otus.homework.DataFactory.correctQuizWithAnswers;
import static ru.otus.homework.DataFactory.interviewAnswer;

@DisplayName("Опрос ворос/ответ - DAO")
class InterviewQuestionAnswerDaoImplTest {

    private Quiz quiz;
    private InterviewAnswer interviewAnswer;

    @BeforeEach
    void SetUp() {
        quiz = correctQuizWithAnswers();
        interviewAnswer = interviewAnswer();
    }

    @DisplayName("возвращается корректный текст ответа")
    @Test
    void shouldReturnCorrectQuestionAnswer() {
        InterviewQuestionAnswer interviewQuestionAnswer = new InterviewQuestionAnswer(quiz, interviewAnswer);
        assertThat(interviewQuestionAnswer.getInterviewAnswer().getAnswer()).isEqualTo(INTERVIEW_ANSWER);
    }

    @DisplayName("возвращается корректный вопрос со списком правильных и вариантов ответов")
    @Test
    void shouldReturnCorrectQuizWithAnswers() {
        InterviewQuestionAnswer interviewQuestionAnswer = new InterviewQuestionAnswer(quiz, interviewAnswer);
        assertThat(interviewQuestionAnswer.getQuiz())
                .extracting(Quiz::getName,
                        toQuizAnswers -> toQuizAnswers.getAnswers().stream().map(QuizAnswer::getName).collect(Collectors.toList()),
                        toQuizCorrectAnswers -> toQuizCorrectAnswers.getCorrectAnswers().stream().map(QuizAnswer::getName).collect(Collectors.toList())
                )
                .containsExactly("Q", Arrays.asList("Q1", "Q2", "Q3", "Q4", "Q5", "Q6", "Q7"), Arrays.asList("Q3", "Q5", "Q6"));
    }
}