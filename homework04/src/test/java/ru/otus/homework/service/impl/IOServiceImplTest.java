package ru.otus.homework.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.homework.service.InputService;
import ru.otus.homework.service.OutputService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.otus.homework.DataFactory.SOME_TEXT;

@DisplayName("Сервис ввода/вывода сообщений")
@SpringBootTest(properties = "spring.shell.interactive.enabled=false")
class IOServiceImplTest {
    @Mock
    private OutputService outputService;
    @Mock
    private InputService inputService;
    @InjectMocks
    private IOServiceImpl ioService;

    @DisplayName("передача сообщения для вывода в консоль")
    @Test
    void shouldTransferCorrectMessageToOutput() {
        ioService.outputString(SOME_TEXT);
        var captor = ArgumentCaptor.forClass(String.class);
        verify(outputService, times(1)).outputString(captor.capture());
        assertEquals(SOME_TEXT, captor.getValue());
    }

    @DisplayName("передача введенного в консоли сообщения")
    @Test
    void shouldTransferCorrectMessageFromConsole() {
        when(inputService.readString()).thenReturn(SOME_TEXT);
        assertEquals(SOME_TEXT, ioService.readString());
    }
}