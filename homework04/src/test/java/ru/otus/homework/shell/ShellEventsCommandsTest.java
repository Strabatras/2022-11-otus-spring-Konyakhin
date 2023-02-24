package ru.otus.homework.shell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.Shell;
import org.springframework.test.annotation.DirtiesContext;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.otus.homework.DataFactory.USER_AUTHORIZATION_NAME;
import static ru.otus.homework.DataFactory.USER_AUTHORIZATION_SURNAME;

@DisplayName("Shell команды")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(properties = {"spring.shell.interactive.enabled=false", "quiz.localization.locale=en"})
class ShellEventsCommandsTest {
    @Autowired
    private Shell shell;

    @DisplayName("должны быть корректные параметры авторизации")
    @ParameterizedTest(name = "{index} - {0}")
    @ValueSource(strings = { "authorization", "a" })
    void  shouldBeCorrectAuthorizationParams(String command) {
        assertThat(shell.evaluate(() -> format("%s %s %s", command, USER_AUTHORIZATION_NAME, USER_AUTHORIZATION_SURNAME))).isNull();
        assertThat(shell.evaluate(() -> format("%s --n %s --s %s", command, USER_AUTHORIZATION_NAME, USER_AUTHORIZATION_SURNAME))).isNull();
    }

    @DisplayName("должны быть корректные параметры запуска")
    @ParameterizedTest(name = "{index} - {0}")
    @ValueSource(strings = { "run", "r" })
    void quizRun(String command) {
        assertThat(shell.evaluate(() -> command)).isNull();
    }

    @DisplayName("должны быть корректные параметры запуска вывода статистики")
    @ParameterizedTest(name = "{index} - {0}")
    @ValueSource(strings = { "statistics", "s" })
    void statisticRun(String command) {
        assertThat(shell.evaluate(() -> command)).isNull();
    }
}