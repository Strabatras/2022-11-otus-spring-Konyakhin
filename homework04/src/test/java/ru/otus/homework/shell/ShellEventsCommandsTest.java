package ru.otus.homework.shell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.Shell;
import org.springframework.test.annotation.DirtiesContext;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.otus.homework.DataFactory.MESSAGE_AUTHORIZATION_CONTINUE_AUTHORIZATION_IS_CORRECT;
import static ru.otus.homework.DataFactory.MESSAGE_AUTHORIZATION_NAME_AND_SURNAME_CAN_NOT_BE_EMPTY;
import static ru.otus.homework.DataFactory.MESSAGE_AUTHORIZATION_NAME_CAN_NOT_BE_EMPTY;
import static ru.otus.homework.DataFactory.MESSAGE_AUTHORIZATION_SURNAME_CAN_NOT_BE_EMPTY;
import static ru.otus.homework.DataFactory.MESSAGE_PLEASE_ANSWER_QUESTIONS;
import static ru.otus.homework.DataFactory.MESSAGE_PLEASE_AUTHORIZE;
import static ru.otus.homework.DataFactory.USER_AUTHORIZATION_NAME;
import static ru.otus.homework.DataFactory.USER_AUTHORIZATION_SURNAME;

@DisplayName("Shell команды")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(properties = {"spring.shell.interactive.enabled=false", "quiz.localization.locale=en"})
class ShellEventsCommandsTest {
    @Autowired
    private Shell shell;

    @DisplayName("выводит строку приглашая продолжить, если авторизация пройдена")
    @Test
    void shouldReturnPromptToContinueIfAuthorizationIsCorrect() {
        String prompt = shell.evaluate(() -> format("authorization --n %s --s %s", USER_AUTHORIZATION_NAME, USER_AUTHORIZATION_SURNAME)).toString();
        assertThat(prompt).isEqualTo(MESSAGE_AUTHORIZATION_CONTINUE_AUTHORIZATION_IS_CORRECT);

        prompt = shell.evaluate(() -> format("a --n %s --s %s", USER_AUTHORIZATION_NAME, USER_AUTHORIZATION_SURNAME)).toString();
        assertThat(prompt).isEqualTo(MESSAGE_AUTHORIZATION_CONTINUE_AUTHORIZATION_IS_CORRECT);
    }

    @DisplayName("выводит сообщение если если при авторизации не ввели фамилию")
    @Test
    void shouldReturnErrorMessageIfAuthorizationFailedSurnameIsEmpty() {
        String prompt = shell.evaluate(() -> format("authorization --n %s", USER_AUTHORIZATION_NAME)).toString();
        assertThat(prompt).isEqualTo(MESSAGE_AUTHORIZATION_SURNAME_CAN_NOT_BE_EMPTY);

        prompt = shell.evaluate(() -> format("authorization %s", USER_AUTHORIZATION_NAME)).toString();
        assertThat(prompt).isEqualTo(MESSAGE_AUTHORIZATION_SURNAME_CAN_NOT_BE_EMPTY);

        prompt = shell.evaluate(() -> format("a --n %s", USER_AUTHORIZATION_NAME)).toString();
        assertThat(prompt).isEqualTo(MESSAGE_AUTHORIZATION_SURNAME_CAN_NOT_BE_EMPTY);

        prompt = shell.evaluate(() -> format("a %s", USER_AUTHORIZATION_NAME)).toString();
        assertThat(prompt).isEqualTo(MESSAGE_AUTHORIZATION_SURNAME_CAN_NOT_BE_EMPTY);
    }

    @DisplayName("выводит сообщение если если при авторизации не ввели имя")
    @Test
    void shouldReturnErrorMessageIfAuthorizationFailedNameIsEmpty() {
        String prompt = shell.evaluate(() -> format("authorization --s %s", USER_AUTHORIZATION_SURNAME)).toString();
        assertThat(prompt).isEqualTo(MESSAGE_AUTHORIZATION_NAME_CAN_NOT_BE_EMPTY);

        prompt = shell.evaluate(() -> format("a --s %s", USER_AUTHORIZATION_SURNAME)).toString();
        assertThat(prompt).isEqualTo(MESSAGE_AUTHORIZATION_NAME_CAN_NOT_BE_EMPTY);
    }

    @DisplayName("выводит сообщение если если при авторизации не ввели имя и фамилию")
    @Test
    void shouldReturnErrorMessageIfAuthorizationFailedNameAndSurnameIsEmpty() {
        String prompt = shell.evaluate(() -> "authorization").toString();
        assertThat(prompt).isEqualTo(MESSAGE_AUTHORIZATION_NAME_AND_SURNAME_CAN_NOT_BE_EMPTY);

        prompt = shell.evaluate(() -> "authorization").toString();
        assertThat(prompt).isEqualTo(MESSAGE_AUTHORIZATION_NAME_AND_SURNAME_CAN_NOT_BE_EMPTY);
    }

    @DisplayName("выводит сообщение если запустили тестирование без предварительной авторизации")
    @Test
    void shouldReturnErrorMessageIfRunWithoutAuthorization() {
        String prompt = shell.evaluate(() -> "run").toString();
        assertThat(prompt).isEqualTo(MESSAGE_PLEASE_AUTHORIZE);

        prompt = shell.evaluate(() -> "r").toString();
        assertThat(prompt).isEqualTo(MESSAGE_PLEASE_AUTHORIZE);
    }

    @DisplayName("выводит сообщение если запустили статистику без предварительно пройденного опроса")
    @Test
    void shouldReturnErrorMessageIfGetStatisticsWithoutRunQuest() {
        String prompt = shell.evaluate(() -> "statistics").toString();
        assertThat(prompt).isEqualTo(MESSAGE_PLEASE_ANSWER_QUESTIONS);

        prompt = shell.evaluate(() -> "statistics").toString();
        assertThat(prompt).isEqualTo(MESSAGE_PLEASE_ANSWER_QUESTIONS);
    }
}