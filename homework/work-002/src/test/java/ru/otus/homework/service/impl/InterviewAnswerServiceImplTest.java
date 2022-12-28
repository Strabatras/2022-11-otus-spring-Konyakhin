package ru.otus.homework.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework.dao.InterviewAnswerDao;
import ru.otus.homework.domain.InterviewAnswer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.otus.homework.DataFactory.INTERVIEW_ANSWER;

@DisplayName("Сервис опроса/ответов")
@ExtendWith(MockitoExtension.class)
class InterviewAnswerServiceImplTest {

    @Mock
    private InterviewAnswerDao interviewAnswerDao;

    @InjectMocks
    private InterviewAnswerServiceImpl interviewAnswerService;

    @DisplayName("возвращает корректный ответ на вопрос")
    @Test
    void shouldReturnCorrectInterviewAnswer() {
        when(interviewAnswerDao.createInterviewAnswer(anyString())).thenReturn(new InterviewAnswer(INTERVIEW_ANSWER));
        assertThat(interviewAnswerService.createInterviewAnswer(anyString()).getAnswer())
                .isEqualTo(INTERVIEW_ANSWER);
        verify(interviewAnswerDao, times(1)).createInterviewAnswer(anyString());
    }
}