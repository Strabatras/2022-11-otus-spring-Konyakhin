package ru.otus.homework.dao;

import java.util.List;

public interface BookAuthorDao {
    void createBatch(Long bookId, List<Long> authorIds);

    void deleteByBookId(Long id);
}
