package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import ru.otus.homework.service.IOService;
import ru.otus.homework.service.InputService;
import ru.otus.homework.service.OutputService;

@RequiredArgsConstructor
public class IOServiceImpl implements IOService, InputService, OutputService {
    private final OutputService outputService;
    private final InputService inputService;

    @Override
    public void outputString(String str) {
        outputService.outputString(str);
    }

    @Override
    public String readString(String prompt) {
        return inputService.readString(prompt);
    }
}