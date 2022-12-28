package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import ru.otus.homework.service.InputService;

import java.io.InputStream;
import java.util.Scanner;

@RequiredArgsConstructor
public class InputServiceImpl implements InputService {
    private final InputStream inputStream;

    @Override
    public String readString() {
        return new Scanner(inputStream).nextLine();
    }
}