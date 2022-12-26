package ru.otus.homework.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.otus.homework.DataFactory.INTERVIEWEE_NAME;
import static ru.otus.homework.DataFactory.INTERVIEWEE_SURNAME;

@DisplayName("Персона")
class PersonalityTest {

    private Personality personality;

    @BeforeEach
    void SetUp() {
       personality = new Personality(INTERVIEWEE_NAME, INTERVIEWEE_SURNAME);
    }

    @DisplayName("корректно возвращается имя персоны")
    @Test
    void shouldReturnCorrectName() {
        assertThat(personality.getName()).isEqualTo(INTERVIEWEE_NAME);
    }

    @DisplayName("корректно возвращается фамилия персоны")
    @Test
    void shouldReturnCorrectSurname() {
        assertThat(personality.getSurname()).isEqualTo(INTERVIEWEE_SURNAME);
    }
}