package ru.otus.homework.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.otus.homework.DataFactory.SOME_TEXT;

@DisplayName("Сервис ввода сообщений")
@ExtendWith(MockitoExtension.class)
class InputServiceImplTest {

    @DisplayName("возврат введенного в консоли сообщения")
    @Test
    void shouldReturnMessageFromConsole() {
        InputServiceImpl inputService = new InputServiceImpl(
                new ByteArrayInputStream(SOME_TEXT.getBytes())
        );
        assertEquals(SOME_TEXT, inputService.readString());
    }
}