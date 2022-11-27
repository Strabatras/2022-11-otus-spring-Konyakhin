package ru.otus.homework.dao.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework.dao.QuizDao;
import ru.otus.homework.domain.Quiz;
import ru.otus.homework.util.DataReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class QuizDaoImplTest {
    @Mock
    private DataReader dataReader;

    private List<List<String>> dataToCheck() {
        return new ArrayList<>(Arrays.asList(
                    new ArrayList<>(Arrays.asList("A", "A1", "A2", "A3")),
                    new ArrayList<>(Arrays.asList("B")),
                    new ArrayList<>(Arrays.asList(" ")),
                    new ArrayList<>(Arrays.asList("C", "C1", "C2", "C3", "C4", "C5")),
                    new ArrayList<>(Arrays.asList("D", "D1", "D2")),
                    new ArrayList<>(Arrays.asList(""))
        ));
    }

    @Test
    void quizzesShouldBeCorrect() {
        final List<List<String>> dataToCheck = dataToCheck();

        lenient().when(dataReader.data()).thenReturn(dataToCheck);
        final QuizDao quizDao = new QuizDaoImpl(dataReader);
        final List<Quiz> quizzes = quizDao.quizzes();
        assertNotNull(quizzes);

        final String[] quizzesNames = dataToCheck.stream()
                .filter(row -> !row.stream().findFirst().get().trim().isEmpty())
                .map(row -> row.stream().findFirst().get())
                .toArray(String[]::new);

        assertEquals(dataToCheck.size() - (dataToCheck.size() - quizzesNames.length), quizzes.size());

        for (int i = 0; i < quizzesNames.length; i++){
            var quiz = quizzes.get(i);
            assertTrue(quiz.getClass().getSimpleName().equals("Quiz"));
            assertEquals(quizzesNames[i], quiz.getName());
            for (int j = 0; j < quiz.getAnswers().size(); j++) {
                var quizAnswer = quiz.getAnswers().get(j);
                assertTrue(quizAnswer.getClass().getSimpleName().equals("QuizAnswer"));
                assertEquals(quizzesNames[i] + ( j + 1 ), quizAnswer.getName());
            }
        }
    }
}