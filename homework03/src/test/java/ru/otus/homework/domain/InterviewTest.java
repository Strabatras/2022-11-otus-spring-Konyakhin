package ru.otus.homework.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static ru.otus.homework.DataFactory.PERSONALITY_NAME;
import static ru.otus.homework.DataFactory.PERSONALITY_SURNAME;
import static ru.otus.homework.DataFactory.interviewQuestionAnswer;
import static ru.otus.homework.DataFactory.interviewQuestionAnswers;
import static ru.otus.homework.DataFactory.personality;

@DisplayName("Опрос")
@SpringBootTest
class InterviewTest {

    private Personality personality;
    private List<InterviewQuestionAnswer> interviewQuestionAnswers;

    @BeforeEach
    void SetUp() {
        personality = personality();
        interviewQuestionAnswers = interviewQuestionAnswers();
    }

    @DisplayName("возвращаются корректные данные вопроса опроса и его ответа")
    @Test
    void shouldReturnCorrectQuestionAnswer() {
        Interview interview = new Interview(personality, new ArrayList<>());
        interview.setQuestionAnswer(interviewQuestionAnswer());

        assertThat(interview.getInterviewQuestionAnswers())
                .hasSize(1)
                .extracting(toQuizName -> toQuizName.getQuiz().getName(),
                        toQuizAnswers -> toQuizAnswers.getQuiz().getAnswers().stream().map(QuizAnswer::getName).collect(Collectors.toList()),
                        toQuizCorrectAnswers -> toQuizCorrectAnswers.getQuiz().getAnswers().stream()
                                .map(QuizAnswer::getCorrectAnswer)
                                .filter(Objects::nonNull)
                                .collect(Collectors.toList()),
                        InterviewQuestionAnswer::getInterviewAnswer
                )
                .containsExactly(tuple("Q", asList("Q1", "Q2", "Q3", "Q4", "Q5", "Q6", "Q7"), asList("Q2", "Q4", "Q6"), "Interview Answer"));
    }

    @DisplayName("возвращаются корректные данные опрашиваемого")
    @Test
    void shouldReturnCorrectPersonality() {
        Interview interview = new Interview(personality, interviewQuestionAnswers);
        assertThat(interview.getPersonality())
                .extracting(Personality::getName, Personality::getSurname)
                .containsExactly(PERSONALITY_NAME, PERSONALITY_SURNAME);
    }

    @DisplayName("возвращаются корректные данные вопросов, вариантов ответов и ответов опрашиваемого")
    @Test
    void shouldReturnCorrectInterviewQuestionAnswer() {
        Interview interview = new Interview(personality, interviewQuestionAnswers);
        assertThat(interview.getInterviewQuestionAnswers())
                .extracting(toQuizName -> toQuizName.getQuiz().getName(),
                        toQuizAnswers -> toQuizAnswers.getQuiz().getAnswers().stream().map(QuizAnswer::getName).collect(Collectors.toList()),
                        toQuizCorrectAnswers -> toQuizCorrectAnswers.getQuiz().getAnswers().stream()
                                .map(QuizAnswer::getCorrectAnswer)
                                .filter(Objects::nonNull)
                                .collect(Collectors.toList()),
                        InterviewQuestionAnswer::getInterviewAnswer
                )
                .containsExactly(
                        tuple("A", asList("A1", "A2", "A3"), List.of("A2"), "Interview Answer A"),
                        tuple("B", asList("B1", "B2", "B3", "B4", "B5"), asList("B2", "B4"), "Interview Answer B"),
                        tuple("C", asList("C1", "C2", "C3", "C4", "C5", "C6", "C7"), asList("C2", "C4", "C6"), "Interview Answer C"),
                        tuple("D", List.of(), List.of(), "Interview Answer D"),
                        tuple("E", List.of("E1"), List.of(), "Interview Answer E")
                );
    }
}