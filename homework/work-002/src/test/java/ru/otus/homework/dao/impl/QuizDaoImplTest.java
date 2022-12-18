package ru.otus.homework.dao.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework.domain.Quiz;
import ru.otus.homework.exception.FileEmptyLineQuizException;
import ru.otus.homework.util.DataReader;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static org.mockito.Mockito.when;
import static ru.otus.homework.DataFactory.expectedReadLinesForCorrectQuizzesFile;

@DisplayName("Вопросы/ответы - DAO")
@ExtendWith(MockitoExtension.class)
class QuizDaoImplTest {

    @Mock
    private DataReader dataReader;
    @InjectMocks
    private QuizDaoImpl quizDao;

    @DisplayName("возвращает корректно заполненный список вопросов для корректного файла с вопросами")
    @Test
    void shouldReturnCorrectArrayListFromCorrectQuizzesFile() throws IOException, FileEmptyLineQuizException {
        when(dataReader.readLines()).thenReturn(expectedReadLinesForCorrectQuizzesFile());
        assertThat(quizDao.getQuizzes())
                .extracting(Quiz::getName, answers -> answers.getAnswers().size())
                .containsExactly(
                        tuple("A", 0),
                        tuple("B", 3),
                        tuple("C", 5),
                        tuple("D", 1),
                        tuple("E", 2)
                );
    }
}