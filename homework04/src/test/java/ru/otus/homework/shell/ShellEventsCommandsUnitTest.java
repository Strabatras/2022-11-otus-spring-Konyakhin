package ru.otus.homework.shell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.homework.domain.Personality;
import ru.otus.homework.service.LocalizationService;
import ru.otus.homework.service.QuizRunnerService;
import ru.otus.homework.util.ShellQuizRunner;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.otus.homework.DataFactory.SOME_TEXT;
import static ru.otus.homework.DataFactory.USER_AUTHORIZATION_NAME;
import static ru.otus.homework.DataFactory.USER_AUTHORIZATION_SURNAME;
import static ru.otus.homework.DataFactory.interview;
import static ru.otus.homework.DataFactory.personality;

@DisplayName("Проверка запуска приложения из shell")
@SpringBootTest(properties = {"spring.shell.interactive.enabled=false", "quiz.localization.locale=en"})
class ShellEventsCommandsUnitTest {
    @Mock
    private LocalizationService localizationService;
    @Mock
    private QuizRunnerService quizRunnerService;
    @Mock
    private ShellQuizRunner shellQuizRunner;
    @InjectMocks
    private ShellEventsCommands shellEventsCommands;

    @DisplayName("возвращает корректное сообщение если авторизация состоялась")
    @Test
    void shouldReturnCorrectMessageIfAuthorizationIsCorrect() {
        ArgumentCaptor<String> param = ArgumentCaptor.forClass(String.class);
        when(localizationService.getMessage(param.capture())).thenReturn(SOME_TEXT);

        assertDoesNotThrow(() -> shellEventsCommands.authorization(USER_AUTHORIZATION_NAME, USER_AUTHORIZATION_SURNAME));
        assertThat(param.getValue()).isEqualTo("identify.you.can.answer.questions");

        verify(shellQuizRunner, times(1)).setPersonality(any(Personality.class));
        verify(localizationService, times(1)).getMessage(anyString());
    }

    @DisplayName("возвращает сообщение об ошибке если при авторизации не указали имя и фамилию")
    @Test
    void shouldReturnErrorMessageIfAuthorizationFailNameAndSurnameIsEmpty() {
        ArgumentCaptor<String> param = ArgumentCaptor.forClass(String.class);
        when(localizationService.getMessage(param.capture())).thenReturn(SOME_TEXT);

        assertDoesNotThrow(() -> shellEventsCommands.authorization(null, null));
        assertThat(param.getAllValues()).isEqualTo(Arrays.asList("identify.name.cant.be.empty", "identify.surname.cant.be.empty"));

        verify(shellQuizRunner, times(1)).setPersonality(any(Personality.class));
        verify(localizationService, times(2)).getMessage(anyString());
    }

    @DisplayName("возвращает сообщение об ошибке если при авторизации не указали фамилию")
    @Test
    void shouldReturnErrorMessageIfAuthorizationFailSurnameIsEmpty() {
        ArgumentCaptor<String> param = ArgumentCaptor.forClass(String.class);
        when(localizationService.getMessage(param.capture())).thenReturn(SOME_TEXT);

        assertDoesNotThrow(() -> shellEventsCommands.authorization(USER_AUTHORIZATION_NAME, null));
        assertThat(param.getValue()).isEqualTo("identify.surname.cant.be.empty");

        verify(shellQuizRunner, times(1)).setPersonality(any(Personality.class));
        verify(localizationService, times(1)).getMessage(anyString());
    }

    @DisplayName("возвращает сообщение об ошибке если при авторизации не указали имя")
    @Test
    void shouldReturnErrorMessageIfAuthorizationFailNameIsEmpty() {
        ArgumentCaptor<String> param = ArgumentCaptor.forClass(String.class);
        when(localizationService.getMessage(param.capture())).thenReturn(SOME_TEXT);

        assertDoesNotThrow(() -> shellEventsCommands.authorization(null, USER_AUTHORIZATION_SURNAME));
        assertThat(param.getValue()).isEqualTo("identify.name.cant.be.empty");

        verify(shellQuizRunner, times(1)).setPersonality(any(Personality.class));
        verify(localizationService, times(1)).getMessage(anyString());
    }


    @DisplayName("возвращает сообщение об ошибке если тестирование проводится без предварительной авторизации")
    @Test
    void shouldReturnErrorMessageIfRunWithoutAuthorization() {
        ArgumentCaptor<String> param = ArgumentCaptor.forClass(String.class);
        when(localizationService.getMessage(param.capture())).thenReturn(SOME_TEXT);
        when(shellQuizRunner.getPersonality()).thenReturn(null);

        assertDoesNotThrow(() -> shellEventsCommands.quizRun());
        assertThat(param.getValue()).isEqualTo("identify.authorized.please");

        verify(shellQuizRunner, times(1)).getPersonality();
        verify(localizationService, times(1)).getMessage(anyString());
        verify(quizRunnerService, never()).runInShell(any());
    }

    @DisplayName("возвращает корректное сообщение если опрос завершен")
    @Test
    void shouldReturnCorrectMessageIfQuestIsFinished() {
        ArgumentCaptor<String> param = ArgumentCaptor.forClass(String.class);
        when(localizationService.getMessage(param.capture())).thenReturn(SOME_TEXT);
        when(shellQuizRunner.getPersonality()).thenReturn(personality());
        doNothing().when(quizRunnerService).runInShell(any());

        assertDoesNotThrow(() -> shellEventsCommands.quizRun());
        assertThat(param.getValue()).isEqualTo("have.access.to.questions.statistics");

        verify(shellQuizRunner, times(1)).getPersonality();
        verify(localizationService, times(1)).getMessage(anyString());
        verify(quizRunnerService, times(1)).runInShell(any());
    }

    @DisplayName("возвращает сообщение об ошибке если статистика вызывается без предварительного прохождения опроса")
    @Test
    void shouldReturnErrorMessageIfStatisticRunWithoutRunQuest() {
        ArgumentCaptor<String> param = ArgumentCaptor.forClass(String.class);
        when(localizationService.getMessage(param.capture())).thenReturn(SOME_TEXT);
        when(shellQuizRunner.getInterview()).thenReturn(null);

        assertDoesNotThrow(() -> shellEventsCommands.statisticRun());
        assertThat(param.getValue()).isEqualTo("please.answer.the.questions");

        verify(shellQuizRunner, times(1)).getInterview();
        verify(localizationService, times(1)).getMessage(anyString());
        verify(quizRunnerService, never()).runOutputStatisticInShell(any());
    }

    @DisplayName("возвращает корректное сообщение о возможности выхода из приложения")
    @Test
    void shouldReturnCorrectMessageIfStatisticIsFinished() {
        ArgumentCaptor<String> param = ArgumentCaptor.forClass(String.class);
        when(localizationService.getMessage(param.capture())).thenReturn(SOME_TEXT);
        when(shellQuizRunner.getInterview()).thenReturn(interview());
        doNothing().when(quizRunnerService).runOutputStatisticInShell(any());

        assertDoesNotThrow(() -> shellEventsCommands.statisticRun());
        assertThat(param.getValue()).isEqualTo("enter.exit.to.complete");

        verify(shellQuizRunner, times(1)).getInterview();
        verify(localizationService, times(1)).getMessage(anyString());
        verify(quizRunnerService, times(1)).runOutputStatisticInShell(any());
    }
}