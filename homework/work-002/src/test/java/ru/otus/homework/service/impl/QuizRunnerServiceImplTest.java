package ru.otus.homework.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework.exception.FileNotFoundQuizException;
import ru.otus.homework.exception.FileReadLineQuizException;
import ru.otus.homework.exception.IOQuizException;
import ru.otus.homework.exception.QuizException;
import ru.otus.homework.service.IOService;
import ru.otus.homework.service.QuizService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.otus.homework.DataFactory.MESSAGE_ANY_ERROR;
import static ru.otus.homework.DataFactory.MESSAGE_CSV_FILE_IS_NOT_FOUND_TO_USER;
import static ru.otus.homework.DataFactory.MESSAGE_CSV_FILE_PREPARATION_ERROR_TO_USER;
import static ru.otus.homework.DataFactory.MESSAGE_CSV_FILE_READING_ERROR_TO_USER;
import static ru.otus.homework.DataFactory.MESSAGE_I_DONT_HAVE_QUESTIONS;
import static ru.otus.homework.DataFactory.MESSAGE_SOMETHING_WENT_WRONG_TO_USER;
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
    void shouldOutputExpectedMessageForEmptyQuizizzList() throws QuizException {
        when(quizService.getQuizzes()).thenReturn(quizzesEmptyList());
        quizRunnerService.run();
        verify(quizService, times(1)).getQuizzes();
        var captor = ArgumentCaptor.forClass(String.class);
        verify(ioService, times(1)).outputString(captor.capture());
        assertEquals(MESSAGE_I_DONT_HAVE_QUESTIONS, captor.getValue());
    }

    @DisplayName("вывод в консоль сообщения пользователю, для необработанных исключений")
    @Test
    void shouldOutputToUserMessageNotInterceptedException() throws QuizException {
        given(quizService.getQuizzes()).willThrow(new RuntimeException(MESSAGE_ANY_ERROR));
        quizRunnerService.run();
        verify(quizService, times(1)).getQuizzes();
        var captor = ArgumentCaptor.forClass(String.class);
        verify(ioService, times(1)).outputString(captor.capture());
        assertEquals(MESSAGE_SOMETHING_WENT_WRONG_TO_USER, captor.getValue());
    }

    @DisplayName("вывод в консоль сообщения пользователю, для ненайденного csv файла")
    @Test
    void shouldOutputToUserMessageFileNotFoundQuizException() throws QuizException {
        given(quizService.getQuizzes()).willThrow(new FileNotFoundQuizException(MESSAGE_ANY_ERROR));
        quizRunnerService.run();
        verify(quizService, times(1)).getQuizzes();
        var captor = ArgumentCaptor.forClass(String.class);
        verify(ioService, times(1)).outputString(captor.capture());
        assertEquals(MESSAGE_CSV_FILE_IS_NOT_FOUND_TO_USER, captor.getValue());
    }

    @DisplayName("вывод в консоль сообщения пользователю, о ошибке доступа к csv файлу")
    @Test
    void shouldOutputToUserMessageIOQuizException() throws QuizException {
        given(quizService.getQuizzes()).willThrow(new IOQuizException(MESSAGE_ANY_ERROR));
        quizRunnerService.run();
        verify(quizService, times(1)).getQuizzes();
        var captor = ArgumentCaptor.forClass(String.class);
        verify(ioService, times(1)).outputString(captor.capture());
        assertEquals(MESSAGE_CSV_FILE_PREPARATION_ERROR_TO_USER, captor.getValue());
    }

    @DisplayName("вывод в консоль сообщения пользователю, о ошибке разбора csv файла")
    @Test
    void shouldOutputToUserMessageFileReadLineQuizException() throws QuizException {
        given(quizService.getQuizzes()).willThrow(new FileReadLineQuizException(MESSAGE_ANY_ERROR));
        quizRunnerService.run();
        verify(quizService, times(1)).getQuizzes();
        var captor = ArgumentCaptor.forClass(String.class);
        verify(ioService, times(1)).outputString(captor.capture());
        assertEquals(MESSAGE_CSV_FILE_READING_ERROR_TO_USER, captor.getValue());
    }
}