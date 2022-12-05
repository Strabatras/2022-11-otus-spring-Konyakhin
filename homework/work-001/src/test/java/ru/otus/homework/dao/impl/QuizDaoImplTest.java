package ru.otus.homework.dao.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework.domain.Quiz;
import ru.otus.homework.util.DataReader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static ru.otus.homework.DataFactory.CLASS_SIMPLE_NAME_QUIZ;
import static ru.otus.homework.DataFactory.CLASS_SIMPLE_NAME_QUIZ_ANSWER;
import static ru.otus.homework.DataFactory.preparedLinesFromFileWithEmptyLines;

@DisplayName("Вопросы/ответы - DAO")
@ExtendWith(MockitoExtension.class)
class QuizDaoImplTest {
    @Mock
    private DataReader dataReader;
    @InjectMocks
    private QuizDaoImpl quizDao;

    @DisplayName("возвращается корректный список")
    @Test
    void shouldBeCreateCorrectQuizzes() {
        final List<List<String>> dataToCheck = preparedLinesFromFileWithEmptyLines();
        when(dataReader.readLines()).thenReturn(dataToCheck);
        final List<Quiz> quizzes = quizDao.getQuizzes();
        assertNotNull(quizzes);

        final String[] quizzesNames = dataToCheck.stream()
                .filter(line -> line.stream().findFirst().orElse("").trim().length() > 0)
                .map(line -> line.stream().findFirst().get())
                .toArray(String[]::new);

        assertEquals(dataToCheck.size() - (dataToCheck.size() - quizzesNames.length), quizzes.size());

        for (int i = 0; i < quizzesNames.length; i++) {
            var quiz = quizzes.get(i);
            assertEquals(CLASS_SIMPLE_NAME_QUIZ, quiz.getClass().getSimpleName());
            assertEquals(quizzesNames[i], quiz.getName());
            for (int j = 0; j < quiz.getAnswers().size(); j++) {
                var quizAnswer = quiz.getAnswers().get(j);
                assertEquals(CLASS_SIMPLE_NAME_QUIZ_ANSWER, quizAnswer.getClass().getSimpleName());
                assertEquals(quizzesNames[i] + (j + 1), quizAnswer.getName());
            }
        }
    }
}