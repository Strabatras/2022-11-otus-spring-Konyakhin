package ru.otus.homework.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static ru.otus.homework.DataFactory.SOME_TEXT;

@DisplayName("Сервис вывода сообщений")
@SpringBootTest
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