package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.service.IOService;
import ru.otus.homework.service.InputService;
import ru.otus.homework.service.OutputService;

@RequiredArgsConstructor
@Service
public class IOServiceImpl implements IOService, InputService, OutputService {
    private final OutputService outputService;
    private final InputService inputService;

    @Override
    public void outputString(String str) {
        outputService.outputString(str);
    }

    @Override
    public String readString() {
        return inputService.readString();
    }
}