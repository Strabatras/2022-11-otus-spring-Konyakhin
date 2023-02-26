package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.dao.AuthorDao;
import ru.otus.homework.domain.Author;
import ru.otus.homework.exception.AuthorNotFoundLibraryException;
import ru.otus.homework.service.AuthorService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorDao authorDao;

    @Override
    public Author findById(Long id) {
        return authorDao.findById(id)
                .orElseThrow(() -> new AuthorNotFoundLibraryException("Автор не найден"));
    }

    @Override
    public List<Author> findByIdList(List<Long> ids) {
        return authorDao.findByIdList(ids);
    }

    @Override
    public List<Author> findAll() {
        return authorDao.findAll();
    }

    @Override
    public List<Author> findByGenreId(Long id) {
        return authorDao.findByGenreId(id);
    }
}