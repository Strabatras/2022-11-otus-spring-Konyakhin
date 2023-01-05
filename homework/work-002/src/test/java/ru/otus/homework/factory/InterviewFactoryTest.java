package ru.otus.homework.factory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.homework.domain.Interview;
import ru.otus.homework.domain.Personality;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.otus.homework.DataFactory.personality;

@DisplayName("Опрос - фабрика")
class InterviewFactoryTest {

    @DisplayName("возвращается корректно заполненные данные персоны")
    @Test
    void shouldReturnCorrectPersonality() {
        Personality personality = personality();
        Interview interview = (new InterviewFactory()).createInterview(personality);
        assertThat(interview.getPersonality()).isEqualTo(personality);
    }

    @DisplayName("возвращается пустой список ответов на вопросы")
    @Test
    void shouldReturnEmptyInterviewQuestionAnswerList() {
        Interview interview = (new InterviewFactory()).createInterview(personality());
        assertThat(interview.getInterviewQuestionAnswers()).isEmpty();
    }
}