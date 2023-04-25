package ru.otus.homework.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Genre {
    private final Long id;
    private final String title;
}