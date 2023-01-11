package ru.otus.homework.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Personality {
    private final String name;
    private final String surname;
}