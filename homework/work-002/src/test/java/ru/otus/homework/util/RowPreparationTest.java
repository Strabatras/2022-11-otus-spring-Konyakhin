package ru.otus.homework.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.homework.domain.Quiz;
import ru.otus.homework.domain.QuizAnswer;
import ru.otus.homework.exception.FileEmptyLineQuizException;
import ru.otus.homework.exception.QuizRuntimeException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.otus.homework.DataFactory.CLASS_SIMPLE_NAME_QUIZ_ANSWER;
import static ru.otus.homework.DataFactory.MESSAGE_CSV_FILE_HAS_EMPTY_LINE;
import static ru.otus.homework.DataFactory.emptyListOfString;
import static ru.otus.homework.DataFactory.preparedLineToQuizWithAnswers;
import static ru.otus.homework.util.RowPreparation.isNotEmptyRow;
import static ru.otus.homework.util.RowPreparation.isNotEmptyRowOrElseThrowException;
import static ru.otus.homework.util.RowPreparation.rowToAnswers;
import static ru.otus.homework.util.RowPreparation.rowToQuiz;
import static ru.otus.homework.util.RowPreparation.stringToQuizAnswer;

class RowPreparationTest {

    @DisplayName("возвращает true для непустой строки списка")
    @Test
    void shouldReturnTrueForNotEmptyRowOfList() {
        List<String> row = emptyListOfString();
        row.add("A");
        assertThat(isNotEmptyRow(row)).isTrue();
    }

    @DisplayName("возвращает false для пустой строки списка")
    @Test
    void shouldReturnFalseForEmptyRowOfList() {
        assertThat(
                isNotEmptyRow(emptyListOfString())
        ).isFalse();
    }

    @DisplayName("возвращает false для строки списка состоящей из пробелов")
    @Test
    void shouldReturnFalseForContainingOnlySpacesRowOfList() {
        assertThat(
                isNotEmptyRow(emptyListOfString())
        ).isFalse();
    }

    @DisplayName("возвращает исключение для пустой или строки состоящей из пробелов")
    @Test
    void shouldReturnFileEmptyLineQuizExceptionForEmptyRowOfList() throws QuizRuntimeException {
        Exception exception = assertThrows(FileEmptyLineQuizException.class, () -> {
            isNotEmptyRowOrElseThrowException(
                    emptyListOfString()
            );
        });
        assertThat(exception.getMessage()).isEqualTo(MESSAGE_CSV_FILE_HAS_EMPTY_LINE);
    }

    @DisplayName("создает корректный ответ на вопрос")
    @Test
    void shouldBeCreatedCorrectAnswer() {
        assertThat(
                stringToQuizAnswer(CLASS_SIMPLE_NAME_QUIZ_ANSWER)
        )
        .extracting(QuizAnswer::getName, QuizAnswer::isCorrect)
        .contains(CLASS_SIMPLE_NAME_QUIZ_ANSWER, false);
    }

    @DisplayName("создает корректный список ответов на вопрос")
    @Test
    void ShouldBeCreatedCorrectAnswerList() {
        assertThat(
            rowToAnswers(preparedLineToQuizWithAnswers())
        )
        .extracting(QuizAnswer::getName, QuizAnswer::isCorrect)
        .containsExactly(
                tuple("R1", false),
                tuple("R2", false),
                tuple("R3", false),
                tuple("R4", false),
                tuple("R5", false)
        );
    }

    @DisplayName("создает корректный вопрос")
    @Test
    void ShouldBeCreatedCorrectQuiz() {
        assertThat(
                rowToQuiz(preparedLineToQuizWithAnswers())
        )
        .extracting(Quiz::getName)
        .isEqualTo("R");
    }
}