package ru.otus.homework.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework.dao.QuizDao;
import ru.otus.homework.domain.Quiz;
import ru.otus.homework.domain.QuizAnswer;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.otus.homework.DataFactory.expectedReadLinesForCorrectQuizzesFile;
import static ru.otus.homework.DataFactory.quizzesWithAnswers;

@DisplayName("Сервис вопросов")
@ExtendWith(MockitoExtension.class)
class QuizServiceImplTest {
    @Mock
    private QuizDao quizDao;
    @InjectMocks
    private QuizServiceImpl quizService;

    @DisplayName("корректно возвращает список вопросов/ответов")
    @Test
    void shouldReturnCorrectQuizzesList() {
        when(quizDao.getQuizzes()).thenReturn(quizzesWithAnswers());
        assertThat(quizService.getQuizzes())
                .extracting(Quiz::getName,
                        answers -> answers.getAnswers().stream().map(QuizAnswer::getName).collect(Collectors.toList()),
                        correctAnswers -> correctAnswers.getAnswers().stream()
                                .map(QuizAnswer::getCorrectAnswer)
                                .filter(Objects::nonNull)
                                .collect(Collectors.toList())
                )
                .containsExactly(
                        tuple("A", asList("A1", "A2", "A3"), List.of("A2")),
                        tuple("B", asList("B1", "B2", "B3", "B4", "B5"), asList("B2", "B4")),
                        tuple("C", asList("C1", "C2", "C3", "C4", "C5", "C6", "C7"), asList("C2", "C4", "C6")),
                        tuple("D", List.of(), List.of()),
                        tuple("E", asList("E1"), List.of())
                );
        verify(quizDao, times(1)).getQuizzes();
    }
}