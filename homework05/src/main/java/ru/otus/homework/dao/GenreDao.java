package ru.otus.homework.dao;

import ru.otus.homework.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDao {
    Optional<Genre> findById(Long id);
    List<Genre> findByIdList(List<Long> ids);
    List<Genre> findAll();
    List<Genre> findByAuthorId(Long id);
}