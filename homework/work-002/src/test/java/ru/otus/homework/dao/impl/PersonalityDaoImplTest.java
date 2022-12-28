package ru.otus.homework.dao.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.homework.dao.PersonalityDao;
import ru.otus.homework.domain.Personality;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.otus.homework.DataFactory.PERSONALITY_NAME;
import static ru.otus.homework.DataFactory.PERSONALITY_SURNAME;

@DisplayName("Персона - DAO")
class PersonalityDaoImplTest {

    @DisplayName("возвращаются корректные данные опрашиваемого")
    @Test
    void shouldReturnCorrectPersonality() {
        PersonalityDao personalityDao = new PersonalityDaoImpl();
        assertThat(personalityDao.createPersonality(PERSONALITY_NAME, PERSONALITY_SURNAME))
                .extracting(Personality::getName, Personality::getSurname)
                .containsExactly(PERSONALITY_NAME, PERSONALITY_SURNAME);
    }
}