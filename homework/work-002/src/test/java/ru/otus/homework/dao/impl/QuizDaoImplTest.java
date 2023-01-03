package ru.otus.homework.dao.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework.domain.Quiz;
import ru.otus.homework.domain.QuizAnswer;
import ru.otus.homework.util.DataReader;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.tuple;
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
        when(dataReader.readLines()).thenReturn(expectedReadLinesForCorrectQuizzesFile());
        assertThat(quizDao.getQuizzes())
                .extracting(Quiz::getName,
                        answers -> answers.getAnswers().stream().map(QuizAnswer::getName).collect(Collectors.toList()),
                        correctAnswers -> correctAnswers.getCorrectAnswers().stream().map(QuizAnswer::getName).collect(Collectors.toList())
                )
                .containsExactly(
                        tuple("A", List.of(), List.of("15")),
                        tuple("B", asList("B1", "B2", "B3"), List.of()),
                        tuple("C", asList("C1", "C2", "C3", "C4", "C5"), asList("C2", "C4")),
                        tuple("D", List.of("D1"), List.of()),
                        tuple("E", asList("E1", "E2"), List.of())
                );
        verify(dataReader, times(1)).readLines();
    }
}