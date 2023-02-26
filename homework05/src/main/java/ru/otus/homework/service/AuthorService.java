package ru.otus.homework.service;

import ru.otus.homework.domain.Author;
import ru.otus.homework.domain.Book;
import ru.otus.homework.domain.Genre;

import java.util.List;

public interface AuthorService {
    Author findById(Long id);
    List<Author> findByIdList(List<Long> ids);
    List<Author> findAll();
    List<Author> findByGenreId(Long id);
}