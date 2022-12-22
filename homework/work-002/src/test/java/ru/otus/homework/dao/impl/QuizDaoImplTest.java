package ru.otus.homework.dao.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework.util.DataReader;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static ru.otus.homework.DataFactory.expectedReadLinesForCorrectQuizzesFile;

@DisplayName("Вопросы/ответы - DAO")
@ExtendWith(MockitoExtension.class)
class QuizDaoImplTest {
    @Mock
    private DataReader dataReader;
    @InjectMocks
    private QuizDaoImpl quizDao;

    @DisplayName("возвращается корректный список строк")
    @Test
    void shouldReturnCorrectRowList(){
        final List<List<String>> dataToCheck = expectedReadLinesForCorrectQuizzesFile();
        when(dataReader.readLines()).thenReturn(dataToCheck);
        assertThat(quizDao.getQuizData()).isEqualTo(dataToCheck);
    }
}