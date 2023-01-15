package ru.otus.homework.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static ru.otus.homework.DataFactory.SOME_TEXT;

@DisplayName("Сервис вывода сообщений")
@ExtendWith(MockitoExtension.class)
class OutputServiceImplTest {
    @Mock
    private PrintStream outputStream;
    @InjectMocks
    private OutputServiceImpl outputService;

    @DisplayName("вывод в консоль сообщения")
    @Test
    void shouldOutputCorrectMessage() {
        outputService.outputString(SOME_TEXT);
        var captor = ArgumentCaptor.forClass(String.class);
        verify(outputStream, times(1)).println(captor.capture());
        assertEquals(SOME_TEXT, captor.getValue());
    }
}