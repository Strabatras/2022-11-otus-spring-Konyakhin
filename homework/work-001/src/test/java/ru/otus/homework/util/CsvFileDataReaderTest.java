package ru.otus.homework.util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvFileDataReaderTest {

    @Test
    void quizFileNameIsEmptyReadLinesShouldBeReturnIllegalArgumentException() {
        DataReader dataReader = new CsvFileDataReader("");
        assertThrows(IllegalArgumentException.class, () -> dataReader.readLines());
    }

    @Test
    void quizFileNameIsWrongReadLinesShouldBeReturnIllegalArgumentException() {
        DataReader dataReader = new CsvFileDataReader("wrong.csv");
        assertThrows(IllegalArgumentException.class, () -> dataReader.readLines());
    }

    @Test
    void quizFileIsEmptyReadLinesShouldBeReturnArrayListType() {
        DataReader dataReader = new CsvFileDataReader("empty-file.csv");
        assertTrue(dataReader.readLines().getClass().getSimpleName().equals("ArrayList"));
    }

    @Test
    void readLinesShouldBeReturnArrayListType() {
        DataReader dataReader = new CsvFileDataReader("quiz-data-reader-test.csv");
        assertTrue(dataReader.readLines().getClass().getSimpleName().equals("ArrayList"));
    }

    @Test
    void readLinesShouldBeReturnCorrect() {
        List<List<String>> expectedData = new ArrayList<>(Arrays.asList(
                new ArrayList<>(Arrays.asList("A")),
                new ArrayList<>(Arrays.asList("B", "B1", "B2", "B3")),
                new ArrayList<>(Arrays.asList("C", "C1", "C2", "C3", "C4", "C5")),
                new ArrayList<>(Arrays.asList("D", "D1")),
                new ArrayList<>(Arrays.asList("E", "E1", "E2"))
        ));
        DataReader dataReader = new CsvFileDataReader("quiz-data-reader-test.csv");
        assertArrayEquals(expectedData.toArray(), dataReader.readLines().toArray());
    }
}