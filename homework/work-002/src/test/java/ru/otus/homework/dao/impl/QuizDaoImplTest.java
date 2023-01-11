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
import ru.otus.homework.util.QuizCsvRowMapper;

import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.otus.homework.DataFactory.correctQuizWithAnswers;
import static ru.otus.homework.DataFactory.correctReadLineForQuizFile;

@DisplayName("Вопросы/ответы - DAO")
@ExtendWith(MockitoExtension.class)
class QuizDaoImplTest {
    @Mock
    private DataReader dataReader;
    @Mock
    private QuizCsvRowMapper quizCsvRowMapper;
    @InjectMocks
    private QuizDaoImpl quizDao;

    @DisplayName("возвращается корректная строка вопроса и ответов к нему")
    @Test
    void shouldReturnCorrectQuizRow() {
        when(dataReader.readLines()).thenReturn(correctReadLineForQuizFile());
        when(quizCsvRowMapper.rowToQuiz(anyList())).thenReturn(correctQuizWithAnswers());
        assertThat(quizDao.getQuizzes())
                .extracting(Quiz::getName,
                        answers -> answers.getAnswers().stream().map(QuizAnswer::getName).collect(Collectors.toList()),
                        correctAnswers -> correctAnswers.getAnswers().stream()
                                .map(QuizAnswer::getCorrectAnswer)
                                .filter(Objects::nonNull)
                                .collect(Collectors.toList())
                )
                .containsExactly(tuple("Q", asList("Q1", "Q2", "Q3", "Q4", "Q5", "Q6", "Q7"), asList("Q2", "Q4", "Q6")));
        verify(quizCsvRowMapper, times(1)).rowToQuiz(anyList());
        verify(dataReader, times(1)).readLines();
    }
}