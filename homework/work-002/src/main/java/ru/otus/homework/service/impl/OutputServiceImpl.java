package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import ru.otus.homework.service.OutputService;

import java.io.PrintStream;

@RequiredArgsConstructor
public class OutputServiceImpl implements OutputService {
    private final PrintStream outputStream;

    @Override
    public void outputString(String str) {
        outputStream.println(str);
    }
}