package ru.otus.homework.dao;

import ru.otus.homework.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    Optional<Author> findById(Long id);

    List<Author> findByIdList(List<Long> ids);

    List<Author> findAll();

    List<Author> findByGenreId(Long id);
}