package ru.otus.homework.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework.dao.InterviewDao;
import ru.otus.homework.domain.Interview;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.otus.homework.DataFactory.personality;

@DisplayName("Сервис опроса")
@ExtendWith(MockitoExtension.class)
class InterviewServiceImplTest {

    @Mock
    private InterviewDao interviewDao;

    @InjectMocks
    private InterviewServiceImpl interviewService;

    @DisplayName("возвращаются корректно созданный экземпляр класса с данными опроса")
    @Test
    void shouldCreateCorrectInterview() {
        when(interviewDao.createInterview(any()))
                .thenReturn(new Interview(personality(), new ArrayList<>()));
        assertThat(interviewService.createInterview(any()))
                .extracting(personality -> personality.getPersonality().getName(),
                        personality -> personality.getPersonality().getSurname(),
                        interviewQuestionAnswers -> interviewQuestionAnswers.getInterviewQuestionAnswers().size()
                )
                .containsExactly("Personality name", "Personality surname", 0);

        verify(interviewDao, times(1)).createInterview(any());
    }
}