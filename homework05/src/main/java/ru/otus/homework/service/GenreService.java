package ru.otus.homework.service;

import ru.otus.homework.domain.Genre;

import java.util.List;

public interface GenreService {
    Genre findById(Long id);

    List<Genre> findByIdList(List<Long> ids);

    List<Genre> findAll();

    List<Genre> findByAuthorId(Long id);
}