package ru.otus.homework.factory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.homework.domain.Personality;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.otus.homework.DataFactory.PERSONALITY_NAME;
import static ru.otus.homework.DataFactory.PERSONALITY_SURNAME;

@DisplayName("Персона - фабрика")
class PersonalityFactoryTest {

    @DisplayName("возвращается корректно заполненный объект персоны")
    @Test
    void createPersonality() {
        Personality personality = (new PersonalityFactory()).createPersonality(PERSONALITY_NAME, PERSONALITY_SURNAME);
        assertThat(personality.getName()).isEqualTo(PERSONALITY_NAME);
        assertThat(personality.getSurname()).isEqualTo(PERSONALITY_SURNAME);
    }
}