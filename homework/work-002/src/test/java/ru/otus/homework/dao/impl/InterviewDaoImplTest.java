package ru.otus.homework.dao.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.homework.domain.Interview;
import ru.otus.homework.domain.InterviewQuestionAnswer;
import ru.otus.homework.domain.Personality;
import ru.otus.homework.domain.QuizAnswer;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static ru.otus.homework.DataFactory.PERSONALITY_NAME;
import static ru.otus.homework.DataFactory.PERSONALITY_SURNAME;
import static ru.otus.homework.DataFactory.interviewQuestionAnswers;
import static ru.otus.homework.DataFactory.personality;

@DisplayName("Опрос - DAO")
class InterviewDaoImplTest {

    private Personality personality;
    private List<InterviewQuestionAnswer> interviewQuestionAnswers;

    @BeforeEach
    void SetUp() {
        personality = personality();
        interviewQuestionAnswers = interviewQuestionAnswers();
    }

    @DisplayName("возвращаются корректные данные опрашиваемого")
    @Test
    void shouldReturnCorrectPersonality() {
        Interview interview = new Interview(personality, interviewQuestionAnswers);
        assertThat(interview.getPersonality())
                .extracting(Personality::getName, Personality::getSurname)
                .contains(PERSONALITY_NAME, PERSONALITY_SURNAME);
    }

    @DisplayName("возвращаются корректные данные вопросов, вариантов ответов и ответов опрашиваемого")
    @Test
    void shouldReturnCorrectInterviewQuestionAnswer() {
        Interview interview = new Interview(personality, interviewQuestionAnswers);

        assertThat(interview.getInterviewQuestionAnswers())
                .extracting(toQuizName -> toQuizName.getQuiz().getName(),
                        toQuizAnswers -> toQuizAnswers.getQuiz().getAnswers().stream().map(QuizAnswer::getName).collect(Collectors.toList()),
                        toQuizCorrectAnswers -> toQuizCorrectAnswers.getQuiz().getCorrectAnswers().stream().map(QuizAnswer::getName).collect(Collectors.toList()),
                        toInterviewAnswer -> toInterviewAnswer.getInterviewAnswer().getAnswer()
                )
                .containsExactly(
                        tuple("A", asList("A1", "A2", "A3"), List.of("A2"), "Interview Answer A"),
                        tuple("B", asList("B1", "B2", "B3", "B4", "B5"), asList("B1", "B3"), "Interview Answer B"),
                        tuple("C", asList("C1", "C2", "C3", "C4", "C5", "C6", "C7"), asList("C2", "C5", "C7"), "Interview Answer C"),
                        tuple("D", List.of(), List.of(), "Interview Answer D"),
                        tuple("E", List.of("E1"), List.of(), "Interview Answer E")
                );
    }
}