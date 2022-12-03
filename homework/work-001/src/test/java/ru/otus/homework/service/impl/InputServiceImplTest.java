package ru.otus.homework.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static ru.otus.homework.DataFactory.SOME_TEXT;

@DisplayName("Сервис ввода сообщений")
@ExtendWith(MockitoExtension.class)
class InputServiceImplTest {
    @Mock
    private Scanner inputStream;
    @InjectMocks
    private InputServiceImpl inputService;

    @DisplayName("возврат введенного в консоли сообщения")
    @Test
    void shouldReturnMessageFromConsole() {
        when(inputStream.nextLine()).thenReturn(SOME_TEXT);
        assertEquals(SOME_TEXT, inputService.readString());
    }
}