package ru.otus.homework.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.otus.homework.domain.Interview;
import ru.otus.homework.domain.Personality;
import ru.otus.homework.domain.Quiz;
import ru.otus.homework.exception.EmptyFileNameQuizException;
import ru.otus.homework.exception.IOQuizException;
import ru.otus.homework.exception.LineValidationQuizException;
import ru.otus.homework.service.IOService;
import ru.otus.homework.service.IdentityService;
import ru.otus.homework.service.InterviewResultService;
import ru.otus.homework.service.PrintService;
import ru.otus.homework.service.QuizService;
import ru.otus.homework.util.QuizUtil;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.otus.homework.DataFactory.MESSAGE_ANY_ERROR;
import static ru.otus.homework.DataFactory.correctQuizWithAnswers;
import static ru.otus.homework.DataFactory.interview;

@DisplayName("Сервис запуска опросов")
@ActiveProfiles
@SpringBootTest(properties = "spring.shell.interactive.enabled=false")
class QuizRunnerServiceImplTest {
    @Mock
    private QuizService quizService;
    @Mock
    private IOService ioService;
    @Mock
    private IdentityService identityService;
    @Mock
    private InterviewResultService interviewResultService;
    @Mock
    private PrintService printService;
    @Mock
    private QuizUtil quizUtil;
    @InjectMocks
    private QuizRunnerServiceImpl quizRunnerService;

    @DisplayName("не должен выбрасывать исключение если в конфигурации не указан файл с вопросами")
    @Test
    void shouldDoesNotThrowEmptyFileNameQuizException(){
        ArgumentCaptor<String> param = ArgumentCaptor.forClass(String.class);
        when(quizService.getQuizzes()).thenThrow(new EmptyFileNameQuizException(MESSAGE_ANY_ERROR));
        doNothing().when(printService).outputLocalizedMessage(param.capture());

        assertDoesNotThrow(() -> quizRunnerService.run());

        assertThat(param.getValue()).isEqualTo("error.message.application.configuration.error");
        verify(quizService, times(1)).getQuizzes();
        verify(printService, times(1)).outputLocalizedMessage(anyString());
    }

    @DisplayName("не должен выбрасывать исключение если не найден файл с вопросам")
    @Test
    void shouldDoesNotThrowFileNotFoundQuizException(){
        ArgumentCaptor<String> param = ArgumentCaptor.forClass(String.class);
        when(quizService.getQuizzes()).thenThrow(new EmptyFileNameQuizException(MESSAGE_ANY_ERROR));
        doNothing().when(printService).outputLocalizedMessage(param.capture());

        assertDoesNotThrow(() -> quizRunnerService.run());

        assertThat(param.getValue()).isEqualTo("error.message.application.configuration.error");
        verify(quizService, times(1)).getQuizzes();
        verify(printService, times(1)).outputLocalizedMessage(anyString());
    }

    @DisplayName("не должен выбрасывать исключение при ошибке валидации строк вопросов")
    @Test
    void shouldDoesNotThrowLineValidationQuizException(){
        ArgumentCaptor<String> param = ArgumentCaptor.forClass(String.class);
        when(quizService.getQuizzes()).thenThrow(new LineValidationQuizException(MESSAGE_ANY_ERROR));
        doNothing().when(printService).outputLocalizedMessage(param.capture());

        assertDoesNotThrow(() -> quizRunnerService.run());

        assertThat(param.getValue()).isEqualTo("error.message.invalid.data.format");
        verify(quizService, times(1)).getQuizzes();
        verify(printService, times(1)).outputLocalizedMessage(anyString());
    }

    @DisplayName("не должен выбрасывать исключение при ошибках ввода/вывода")
    @Test
    void shouldDoesNotThrowIOQuizException(){
        ArgumentCaptor<String> param = ArgumentCaptor.forClass(String.class);
        when(quizService.getQuizzes()).thenThrow(new IOQuizException(MESSAGE_ANY_ERROR));
        doNothing().when(printService).outputLocalizedMessage(param.capture());

        assertDoesNotThrow(() -> quizRunnerService.run());

        assertThat(param.getValue()).isEqualTo("error.message.error.reading.data");
        verify(quizService, times(1)).getQuizzes();
        verify(printService, times(1)).outputLocalizedMessage(anyString());
    }


    @DisplayName("не должен выбрасывать исключение если список вопросов пуст")
    @Test
    void shouldDoesNotThrowEmptyDataQuizException() {
        ArgumentCaptor<String> param = ArgumentCaptor.forClass(String.class);
        when(quizService.getQuizzes()).thenReturn(new ArrayList<>());
        doNothing().when(printService).outputLocalizedMessage(param.capture());

        assertDoesNotThrow(() -> quizRunnerService.run());

        assertThat(param.getValue()).isEqualTo("error.message.i.dont.have.questions");
        verify(quizService, times(1)).getQuizzes();
        verify(printService, times(1)).outputLocalizedMessage(anyString());
    }

    @DisplayName("должно вызываться предложение идентификации")
    @Test
    void shouldCallIdentifyYourselfMessage(){
        ArgumentCaptor<String> param = ArgumentCaptor.forClass(String.class);
        when(quizUtil.getInterview(any(Personality.class))).thenReturn(interview());
        when(quizService.getQuizzes()).thenReturn(List.of(correctQuizWithAnswers()));
        doNothing().when(printService).outputLocalizedMessage(param.capture());

        assertDoesNotThrow(() -> quizRunnerService.run());

        assertThat(param.getValue()).isEqualTo("identify.yourself");
        verify(quizService, times(1)).getQuizzes();
        verify(quizUtil, times(1)).getInterview(any(Personality.class));
        verify(quizUtil, times(1)).quizInterview(any(Quiz.class), any(Interview.class));
        verify(printService, times(1)).outputLocalizedMessage(anyString());
    }

    @DisplayName("должен корректно запускаться")
    @Test
    void ShouldCorrectlyRun(){
        when(quizUtil.getInterview(any(Personality.class))).thenReturn(interview());
        when(quizService.getQuizzes()).thenReturn(List.of(correctQuizWithAnswers()));
        when(ioService.readString()).thenReturn("");
        doNothing().when(printService).outputLocalizedMessage(anyString());
        when(identityService.askName()).thenReturn("Name");
        when(identityService.askSurname()).thenReturn("Surname");
        doNothing().when(interviewResultService).printStatistic(any(Interview.class));

        assertDoesNotThrow(() -> quizRunnerService.run());

        verify(quizUtil, times(1)).getInterview(any(Personality.class));
        verify(quizUtil, times(1)).quizInterview(any(Quiz.class), any(Interview.class));
        verify(quizService, times(1)).getQuizzes();
        verify(printService, times(1)).outputLocalizedMessage(anyString());
        verify(identityService, times(1)).askName();
        verify(identityService, times(1)).askSurname();
        verify(interviewResultService, times(1)).printStatistic(any(Interview.class));
    }
}