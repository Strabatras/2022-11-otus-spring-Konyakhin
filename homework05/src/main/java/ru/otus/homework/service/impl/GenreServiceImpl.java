package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.dao.GenreDao;
import ru.otus.homework.domain.Genre;
import ru.otus.homework.exception.AuthorNotFoundLibraryException;
import ru.otus.homework.exception.GenreNotFoundLibraryException;
import ru.otus.homework.service.GenreService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;

    @Override
    public Genre findById(Long id) {
        return genreDao.findById(id)
                .orElseThrow(() -> new GenreNotFoundLibraryException("Автор не найден"));
    }

    @Override
    public List<Genre> findByIdList(List<Long> ids) {
        return genreDao.findByIdList(ids);
    }

    @Override
    public List<Genre> findAll() {
        return genreDao.findAll();
    }

    @Override
    public List<Genre> findByAuthorId(Long id) {
        return genreDao.findByAuthorId(id);
    }
}