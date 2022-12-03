package ru.otus.homework.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework.service.IOService;
import ru.otus.homework.service.QuizService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.otus.homework.DataFactory.MESSAGE_ANY_ERROR;
import static ru.otus.homework.DataFactory.MESSAGE_I_DONT_HAVE_QUESTIONS;
import static ru.otus.homework.DataFactory.quizzesEmptyList;

@DisplayName("Запуск приложения тестирования студентов")
@ExtendWith(MockitoExtension.class)
class QuizRunnerServiceImplTest {
    @Mock
    private QuizService quizService;
    @Mock
    private IOService ioService;
    @InjectMocks
    private QuizRunnerServiceImpl quizRunnerService;

    @DisplayName("вывод конкретного сообщения для пустого списка вопросов")
    @Test
    void shouldOutputExpectedMessageForEmptyQuizizzList() {
        when(quizService.quizzes()).thenReturn(quizzesEmptyList());
        quizRunnerService.run();
        verify(quizService, times(1)).quizzes();
        var captor = ArgumentCaptor.forClass(String.class);
        verify(ioService, times(1)).outputString(captor.capture());
        assertEquals(MESSAGE_I_DONT_HAVE_QUESTIONS, captor.getValue());
    }

    @DisplayName("вывод в консоль сообщения исключения")
    @Test
    void shouldOutputExpectedExceptionMessage() {
        given(quizService.quizzes()).willThrow(new RuntimeException(MESSAGE_ANY_ERROR));
        quizRunnerService.run();
        verify(quizService, times(1)).quizzes();
        var captor = ArgumentCaptor.forClass(String.class);
        verify(ioService, times(1)).outputString(captor.capture());
        assertEquals(MESSAGE_ANY_ERROR, captor.getValue());
    }
}