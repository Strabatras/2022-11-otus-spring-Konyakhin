package ru.otus.homework.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework.dao.QuizDao;
import ru.otus.homework.domain.Quiz;
import ru.otus.homework.domain.QuizAnswer;
import ru.otus.homework.service.QuizService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuizServiceImplTest {
    @Mock
    private QuizDao quizDao;
    private QuizService quizService;

    @BeforeEach
    void setUp() {
        quizService = new QuizServiceImpl(quizDao);
    }

    @Test
    void quizzesShouldBeCorrect() {
        final List<Quiz> quizzes = new ArrayList<>();
        final List<QuizAnswer> answers = new ArrayList<>();
        answers.add(new QuizAnswer("QuizAnswerName", false));
        quizzes.add(new Quiz("QuizName", answers));

        when(quizDao.quizzes()).thenReturn(quizzes);
        assertEquals(quizzes, quizService.quizzes());
    }
}