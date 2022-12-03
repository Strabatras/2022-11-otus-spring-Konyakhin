package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import ru.otus.homework.service.InputService;

import java.util.Scanner;

@RequiredArgsConstructor
public class InputServiceImpl implements InputService {
    private final Scanner inputStream;

    @Override
    public String readString() {
        return inputStream.nextLine();
    }
}