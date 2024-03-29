package ru.otus.homework.dao;

import ru.otus.homework.domain.Book;
import ru.otus.homework.dto.BookDTO;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    Optional<Book> findById(Long id);

    List<Book> findAll();

    List<Book> findByAuthorId(Long id);

    List<Book> findByGenreId(Long id);

    void deleteById(Long id);

    void update(BookDTO bookDTO);

    void create(BookDTO bookDTO);
}