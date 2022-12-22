package ru.otus.homework.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.homework.exception.IllegalArgumentQuizException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.otus.homework.DataFactory.CLASS_SIMPLE_NAME_ARRAY_LIST;
import static ru.otus.homework.DataFactory.CSV_CORRECT_QUIZZES_FILE;
import static ru.otus.homework.DataFactory.CSV_EMPTY_FILE;
import static ru.otus.homework.DataFactory.CSV_EMPTY_NAME_OF_FILE;
import static ru.otus.homework.DataFactory.CSV_NON_EXISTENT_FILE;
import static ru.otus.homework.DataFactory.MESSAGE_CSV_FILE_IS_EMPTY_FOUND;
import static ru.otus.homework.DataFactory.MESSAGE_CSV_FILE_IS_NOT_FOUND;
import static ru.otus.homework.DataFactory.expectedReadLinesForCorrectQuizzesFile;

@DisplayName("Разбор csv файлов")
class CsvFileDataReaderTest {

    @DisplayName("выбрасывает конкретное исключение 'IllegalArgumentQuizException' если имя файла пустое")
    @Test
    void shouldReturnIllegalArgumentExceptionForEmptyFileName() {
        DataReader dataReader = new CsvFileDataReader(CSV_EMPTY_NAME_OF_FILE);
        var exception = assertThrows(IllegalArgumentQuizException.class, () -> dataReader.readLines());
        assertEquals(MESSAGE_CSV_FILE_IS_EMPTY_FOUND, exception.getMessage());
    }

    @DisplayName("выбрасывает конкретное исключение 'IllegalArgumentQuizException' если указано имя несуществующего файла")
    @Test
    void shouldReturnIllegalArgumentExceptionForWrongFileName() {
        DataReader dataReader = new CsvFileDataReader(CSV_NON_EXISTENT_FILE);
        var exception = assertThrows(IllegalArgumentQuizException.class, () -> dataReader.readLines());
        assertEquals(MESSAGE_CSV_FILE_IS_NOT_FOUND, exception.getMessage());
    }

    @DisplayName("возвращает пустой ArrayList если разбираемый файл пуст")
    @Test
    void shouldReturnEmptyArrayLisIfFileIsEmpty() {
        DataReader dataReader = new CsvFileDataReader(CSV_EMPTY_FILE);
        var lines = dataReader.readLines();
        assertEquals(CLASS_SIMPLE_NAME_ARRAY_LIST, lines.getClass().getSimpleName());
        assertTrue(lines.isEmpty());
    }

    @DisplayName("возвращает не пустой ArrayList если разбираемый файл не пуст")
    @Test
    void shouldReturnNotEmptyArrayLisIfFileIsNotEmpty() {
        DataReader dataReader = new CsvFileDataReader(CSV_CORRECT_QUIZZES_FILE);
        var lines = dataReader.readLines();
        assertEquals(CLASS_SIMPLE_NAME_ARRAY_LIST, lines.getClass().getSimpleName());
        assertTrue(!lines.isEmpty());
    }

    @DisplayName("возвращает корректно заполненный ArrayList для корректного файла с вопросами")
    @Test
    void shouldReturnCorrectArrayListForCorrectQuizzesFile() {
        List<List<String>> expected = expectedReadLinesForCorrectQuizzesFile();
        DataReader dataReader = new CsvFileDataReader(CSV_CORRECT_QUIZZES_FILE);
        var lines = dataReader.readLines();
        assertEquals(CLASS_SIMPLE_NAME_ARRAY_LIST, lines.getClass().getSimpleName());
        assertArrayEquals(expected.toArray(), lines.toArray());
    }
}