package ru.otus.homework.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class Book {
    private final Long id;
    private final String title;
    private final Date releaseDate;
    private final List<Genre> genres;
    private final List<Author> authors;
}