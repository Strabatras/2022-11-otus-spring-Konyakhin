package ru.otus.homework.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.otus.homework.DataFactory.PERSONALITY_NAME;
import static ru.otus.homework.DataFactory.PERSONALITY_SURNAME;
import static ru.otus.homework.DataFactory.personality;

@DisplayName("Персона")
@SpringBootTest(properties = "spring.shell.interactive.enabled=false")
class PersonalityTest {

    private Personality personality;

    @BeforeEach
    void SetUp() {
       personality = personality();
    }

    @DisplayName("корректно возвращается имя персоны")
    @Test
    void shouldReturnCorrectName() {
        assertThat(personality.getName()).isEqualTo(PERSONALITY_NAME);
    }

    @DisplayName("корректно возвращается фамилия персоны")
    @Test
    void shouldReturnCorrectSurname() {
        assertThat(personality.getSurname()).isEqualTo(PERSONALITY_SURNAME);
    }
}