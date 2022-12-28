package ru.otus.homework.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework.dao.PersonalityDao;
import ru.otus.homework.domain.Personality;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.otus.homework.DataFactory.personality;

@DisplayName("Сервис персона")
@ExtendWith(MockitoExtension.class)
class PersonalityServiceImplTest {

    @Mock
    private PersonalityDao personalityDao;

    @InjectMocks
    private PersonalityServiceImpl personalityService;

    @DisplayName("возвращаются корректно созданный экземпляр класса персоны")
    @Test
    void shouldCreateCorrectPersonality() {
        Personality personality = personality();
        when(personalityDao.createPersonality(anyString(), anyString()))
                .thenReturn(personality);
        assertThat(personalityService.createPersonality(anyString(), anyString()))
                .isEqualTo(personality);
        verify(personalityDao, times(1)).createPersonality(anyString(), anyString());
    }
}