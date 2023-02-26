package ru.otus.homework.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.homework.domain.Interview;
import ru.otus.homework.domain.Personality;
import ru.otus.homework.domain.Quiz;
import ru.otus.homework.service.LocalizationService;
import ru.otus.homework.service.PrintService;
import ru.otus.homework.service.QuizService;
import ru.otus.homework.util.QuizUtil;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.otus.homework.DataFactory.MESSAGE_AUTHORIZATION_CONTINUE_AUTHORIZATION_IS_CORRECT;
import static ru.otus.homework.DataFactory.MESSAGE_AUTHORIZATION_NAME_AND_SURNAME_CAN_NOT_BE_EMPTY;
import static ru.otus.homework.DataFactory.MESSAGE_AUTHORIZATION_NAME_CAN_NOT_BE_EMPTY;
import static ru.otus.homework.DataFactory.MESSAGE_AUTHORIZATION_SURNAME_CAN_NOT_BE_EMPTY;
import static ru.otus.homework.DataFactory.USER_AUTHORIZATION_NAME;
import static ru.otus.homework.DataFactory.USER_AUTHORIZATION_SURNAME;
import static ru.otus.homework.DataFactory.correctQuizWithAnswers;
import static ru.otus.homework.DataFactory.interview;

@DisplayName("Сервис запуска опросов в shell")
@SpringBootTest(properties = "spring.shell.interactive.enabled=false")
class QuizShellRunnerServiceImplTest {
    @Mock
    private QuizService quizService;
    @Mock
    private QuizUtil quizUtil;
    @Mock
    private LocalizationService localizationService;
    @Mock
    private PrintService printService;
    @InjectMocks
    private QuizShellRunnerServiceImpl quizShellRunnerService;

    @DisplayName("выводит строку приглашая продолжить, если авторизация пройдена")
    @Test
    void shouldReturnPromptToContinueIfAuthorizationIsCorrect() {
        ArgumentCaptor<String> param = ArgumentCaptor.forClass(String.class);
        when(localizationService.getMessage("identify.you.can.answer.questions"))
                .thenReturn(MESSAGE_AUTHORIZATION_CONTINUE_AUTHORIZATION_IS_CORRECT);

        quizShellRunnerService.authorizationRun(USER_AUTHORIZATION_NAME, USER_AUTHORIZATION_SURNAME);
        verify(localizationService, times(1)).getMessage(anyString());
        verify(printService, times(1)).outputMessage(param.capture());

        assertThat(param.getValue()).isEqualTo(MESSAGE_AUTHORIZATION_CONTINUE_AUTHORIZATION_IS_CORRECT);
    }

    @DisplayName("выводит сообщение если если при авторизации не ввели фамилию")
    @Test
    void shouldReturnErrorMessageIfAuthorizationFailedSurnameIsEmpty() {
        ArgumentCaptor<String> param = ArgumentCaptor.forClass(String.class);
        when(localizationService.getMessage("identify.surname.cant.be.empty"))
                .thenReturn(MESSAGE_AUTHORIZATION_SURNAME_CAN_NOT_BE_EMPTY);

        quizShellRunnerService.authorizationRun(USER_AUTHORIZATION_NAME, "");
        verify(localizationService, times(1)).getMessage(anyString());
        verify(printService, times(1)).outputMessage(param.capture());

        assertThat(param.getValue()).isEqualTo(MESSAGE_AUTHORIZATION_SURNAME_CAN_NOT_BE_EMPTY);
    }

    @DisplayName("выводит сообщение если если при авторизации не ввели имя")
    @Test
    void shouldReturnErrorMessageIfAuthorizationFailedNameIsEmpty() {
        ArgumentCaptor<String> param = ArgumentCaptor.forClass(String.class);
        when(localizationService.getMessage("identify.name.cant.be.empty"))
                .thenReturn(MESSAGE_AUTHORIZATION_NAME_CAN_NOT_BE_EMPTY);

        quizShellRunnerService.authorizationRun("", USER_AUTHORIZATION_SURNAME);
        verify(localizationService, times(1)).getMessage(anyString());
        verify(printService, times(1)).outputMessage(param.capture());

        assertThat(param.getValue()).isEqualTo(MESSAGE_AUTHORIZATION_NAME_CAN_NOT_BE_EMPTY);
    }

    @DisplayName("выводит сообщение если если при авторизации не ввели имя и фамилию")
    @Test
    void shouldReturnErrorMessageIfAuthorizationFailedNameAndSurnameIsEmpty() {
        ArgumentCaptor<String> param = ArgumentCaptor.forClass(String.class);
        when(localizationService.getMessage("identify.name.cant.be.empty"))
                .thenReturn(MESSAGE_AUTHORIZATION_NAME_CAN_NOT_BE_EMPTY);
        when(localizationService.getMessage("identify.surname.cant.be.empty"))
                .thenReturn(MESSAGE_AUTHORIZATION_SURNAME_CAN_NOT_BE_EMPTY);

        quizShellRunnerService.authorizationRun("", "");
        verify(localizationService, times(2)).getMessage(anyString());
        verify(printService, times(1)).outputMessage(param.capture());

        assertThat(param.getValue()).isEqualTo(MESSAGE_AUTHORIZATION_NAME_AND_SURNAME_CAN_NOT_BE_EMPTY);
    }

    @DisplayName("выводит сообщение если запустили тестирование без предварительной авторизации")
    @Test
    void shouldReturnErrorMessageIfRunWithoutAuthorization() {
        ArgumentCaptor<String> param = ArgumentCaptor.forClass(String.class);
        doNothing().when(printService).outputLocalizedMessage(param.capture());

        quizShellRunnerService.quizRun();
        verify(printService, times(1)).outputLocalizedMessage(anyString());
        assertThat(param.getValue()).isEqualTo("identify.authorized.please");
    }

    @Test
    void shouldReturnCorrectMessageWhenQuestIsFinished(){
        ArgumentCaptor<String> param = ArgumentCaptor.forClass(String.class);
        doNothing().when(printService).outputLocalizedMessage(param.capture());
        when(quizService.getQuizzes()).thenReturn(Arrays.asList(correctQuizWithAnswers()));
        when(quizUtil.getInterview(any(Personality.class))).thenReturn(interview());
        doNothing().when(printService).outputMessage(anyString());

        quizShellRunnerService.authorizationRun(USER_AUTHORIZATION_NAME, USER_AUTHORIZATION_SURNAME);
        quizShellRunnerService.quizRun();

        verify(quizService, times(1)).getQuizzes();
        verify(printService, times(2)).outputMessage(anyString());
        verify(quizUtil, times(1)).getInterview(any(Personality.class));
        verify(quizUtil, times(1)).quizInterview(any(Quiz.class), any(Interview.class));
        verify(printService, times(1)).outputLocalizedMessage(anyString());
        assertThat(param.getValue()).isEqualTo("have.access.to.questions.statistics");
    }

    @Test
    void statisticRun() {
    }
}