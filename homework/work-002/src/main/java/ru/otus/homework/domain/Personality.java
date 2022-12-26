package ru.otus.homework.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class Personality {
    private final String name;
    private final String surname;
}