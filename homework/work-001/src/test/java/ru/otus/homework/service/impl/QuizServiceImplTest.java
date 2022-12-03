package ru.otus.homework.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework.dao.QuizDao;
import ru.otus.homework.domain.Quiz;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
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
        final List<Quiz> quizzes = quizzesWithAnswers();
        when(quizDao.quizzes()).thenReturn(quizzes);
        assertEquals(quizzes, quizService.quizzes());
    }
}