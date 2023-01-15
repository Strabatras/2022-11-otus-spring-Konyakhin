package ru.otus.homework.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework.exception.EmptyFileNameQuizException;
import ru.otus.homework.exception.FileNotFoundQuizException;
import ru.otus.homework.exception.IOQuizException;
import ru.otus.homework.exception.LineValidationQuizException;
import ru.otus.homework.service.IOService;
import ru.otus.homework.service.QuizService;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.otus.homework.DataFactory.MESSAGE_ANY_ERROR;
import static ru.otus.homework.DataFactory.MESSAGE_APPLICATION_CONFIGURATION_ERROR;
import static ru.otus.homework.DataFactory.MESSAGE_INVALID_DATA_FORMAT_QUESTIONS_ERROR;
import static ru.otus.homework.DataFactory.MESSAGE_I_DONT_HAVE_QUESTIONS__ERROR;
import static ru.otus.homework.DataFactory.MESSAGE_READING_DATA_QUESTIONS_ERROR;

@DisplayName("Сервис запуска опросов")
@ExtendWith(MockitoExtension.class)
class QuizRunnerServiceImplTest {

    @Mock
    private QuizService quizService;
    @Mock
    private IOService ioService;
    @InjectMocks
    private QuizRunnerServiceImpl quizRunnerService;

    @DisplayName("возвращает корректное сообщение пользователю для 'IllegalArgumentQuizException' исключения")
    @Test
    void shouldOutputEmptyFileNameQuizExceptionToUser(){
        given(quizService.getQuizzes()).willThrow(new EmptyFileNameQuizException(MESSAGE_ANY_ERROR));
        quizRunnerService.run();
        verify(quizService, times(1)).getQuizzes();
        var captor = ArgumentCaptor.forClass(String.class);
        verify(ioService, times(1)).outputString(captor.capture());
        assertEquals(MESSAGE_APPLICATION_CONFIGURATION_ERROR, captor.getValue());
    }

    @DisplayName("возвращает корректное сообщение пользователю для 'FileNotFoundQuizException' исключения")
    @Test
    void shouldOutputFileNotFoundQuizExceptionToUser(){
        given(quizService.getQuizzes()).willThrow(new FileNotFoundQuizException(MESSAGE_ANY_ERROR));
        quizRunnerService.run();
        verify(quizService, times(1)).getQuizzes();
        var captor = ArgumentCaptor.forClass(String.class);
        verify(ioService, times(1)).outputString(captor.capture());
        assertEquals(MESSAGE_APPLICATION_CONFIGURATION_ERROR, captor.getValue());
    }

    @DisplayName("возвращает корректное сообщение пользователю для 'LineValidationQuizException' исключения")
    @Test
    void shouldOutputCsvValidationExceptionToUser(){
        given(quizService.getQuizzes()).willThrow(new LineValidationQuizException(MESSAGE_ANY_ERROR));
        quizRunnerService.run();
        verify(quizService, times(1)).getQuizzes();
        var captor = ArgumentCaptor.forClass(String.class);
        verify(ioService, times(1)).outputString(captor.capture());
        assertEquals(MESSAGE_INVALID_DATA_FORMAT_QUESTIONS_ERROR, captor.getValue());
    }

    @DisplayName("возвращает корректное сообщение пользователю для 'IOQuizException' исключения")
    @Test
    void shouldOutputIOQuizExceptionToUser(){
        given(quizService.getQuizzes()).willThrow(new IOQuizException(MESSAGE_ANY_ERROR));
        quizRunnerService.run();
        verify(quizService, times(1)).getQuizzes();
        var captor = ArgumentCaptor.forClass(String.class);
        verify(ioService, times(1)).outputString(captor.capture());
        assertEquals(MESSAGE_READING_DATA_QUESTIONS_ERROR, captor.getValue());
    }

    @DisplayName("возвращает корректное сообщение пользователю для 'EmptyDataQuizException' исключения")
    @Test
    void shouldOutputEmptyDataQuizExceptionoUser(){
        when(quizService.getQuizzes()).thenReturn(new ArrayList<>());
        quizRunnerService.run();
        verify(quizService, times(1)).getQuizzes();
        var captor = ArgumentCaptor.forClass(String.class);
        verify(ioService, times(1)).outputString(captor.capture());
        assertEquals(MESSAGE_I_DONT_HAVE_QUESTIONS__ERROR, captor.getValue());
    }
}