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

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.Mockito.when;
import static ru.otus.homework.DataFactory.expectedReadLinesForCorrectQuizzesFile;

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
        when(quizDao.getQuizData()).thenReturn(expectedReadLinesForCorrectQuizzesFile());
        assertThat(quizService.getQuizzes())
                .extracting(Quiz::getName,
                        answers -> {
                            return answers.getAnswers().stream().map(QuizAnswer::getName).collect(Collectors.toList());
                        },
                        correctAnswers -> {
                            return correctAnswers.getCorrectAnswers().stream().map(QuizAnswer::getName).collect(Collectors.toList());
                        }
                )
                .containsExactly(
                        tuple("A", Arrays.asList(), Arrays.asList("15")),
                        tuple("B", Arrays.asList("B1", "B2", "B3"), Arrays.asList()),
                        tuple("C", Arrays.asList("C1", "C2", "C3", "C4", "C5"), Arrays.asList("C2", "C4")),
                        tuple("D", Arrays.asList("D1"), Arrays.asList()),
                        tuple("E", Arrays.asList("E1", "E2"), Arrays.asList())
                )
        ;
    }
}