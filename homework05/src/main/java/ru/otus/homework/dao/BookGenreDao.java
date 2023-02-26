package ru.otus.homework.dao;

import java.util.List;

public interface BookGenreDao {
    void createBatch(Long bookId, List<Long> genreIds);

    void deleteByBookId(Long id);
}