package ru.otus.homework.service;

import ru.otus.homework.domain.Book;
import ru.otus.homework.dto.BookDTO;

import java.util.List;

public interface BookService {
    Book findById(Long id);

    List<Book> findAll();

    List<Book> findByAuthorId(Long id);

    List<Book> findByGenreId(Long id);

    void deleteById(Long id);

    void update(BookDTO bookDTO);

    void create(BookDTO bookDTO);
}