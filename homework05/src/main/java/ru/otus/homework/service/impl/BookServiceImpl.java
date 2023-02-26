package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.dao.BookAuthorDao;
import ru.otus.homework.dao.BookDao;
import ru.otus.homework.dao.BookGenreDao;
import ru.otus.homework.domain.Author;
import ru.otus.homework.domain.Book;
import ru.otus.homework.domain.Genre;
import ru.otus.homework.dto.BookDTO;
import ru.otus.homework.exception.BookNotFoundLibraryException;
import ru.otus.homework.exception.GenreNotFoundLibraryException;
import ru.otus.homework.service.AuthorService;
import ru.otus.homework.service.BookService;
import ru.otus.homework.service.GenreService;

import java.util.List;

import static java.lang.String.format;
import static ru.otus.homework.helper.AuthorHelper.fakeAuthorIds;
import static ru.otus.homework.helper.GenreHelper.fakeGenreIds;
import static ru.otus.homework.helper.LongHelper.listToStringArray;
import static ru.otus.homework.helper.LongHelper.listToUniqueList;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;
    private final BookGenreDao bookGenreDao;
    private final BookAuthorDao bookAuthorDao;
    private final GenreService genreService;
    private final AuthorService authorService;

    @Override
    public Book findById(Long id) {
        return bookDao.findById(id)
                .orElseThrow(() -> new BookNotFoundLibraryException("Книга не найдена"));
    }

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    public List<Book> findByAuthorId(Long id) {
        return bookDao.findByAuthorId(id);
    }

    @Override
    public List<Book> findByGenreId(Long id) {
        return bookDao.findByGenreId(id);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        bookAuthorDao.deleteByBookId(id);
        bookGenreDao.deleteByBookId(id);
        bookDao.deleteById(id);
    }

    @Transactional
    @Override
    public void update(BookDTO bookDTO) {
        final Book book = bookDao.findById(bookDTO.getId())
                .orElseThrow(() -> new BookNotFoundLibraryException("Книга не найдена"));
        if (bookDTO.getGenreIds().size() > 0) {
            checkValidGenreIds(bookDTO.getGenreIds());
            bookGenreDao.deleteByBookId(bookDTO.getId());
            bookGenreDao.createBatch(bookDTO.getId(), bookDTO.getAuthorIds());
        }
        if (bookDTO.getAuthorIds().size() > 0) {
            checkValidAuthorIds(bookDTO.getAuthorIds());
            bookAuthorDao.deleteByBookId(bookDTO.getId());
            bookAuthorDao.createBatch(bookDTO.getId(), bookDTO.getAuthorIds());
        }
        if (bookDTO.getTitle() == null) {
            bookDTO.setTitle(book.getTitle());
        }
        if (bookDTO.getReleaseDate() == null) {
            bookDTO.setReleaseDate(book.getReleaseDate());
        }
        bookDao.update(bookDTO);
    }

    @Transactional
    @Override
    public void create(BookDTO bookDTO) {
        bookDao.create(bookDTO);
        if (bookDTO.getGenreIds().size() > 0) {
            checkValidGenreIds(bookDTO.getGenreIds());
            bookGenreDao.createBatch(bookDTO.getId(), bookDTO.getAuthorIds());
        }
        if (bookDTO.getAuthorIds().size() > 0) {
            checkValidAuthorIds(bookDTO.getAuthorIds());
            bookAuthorDao.createBatch(bookDTO.getId(), bookDTO.getAuthorIds());
        }
    }

    private void checkValidGenreIds(List<Long> genreIds) {
        if (genreIds.size() != listToUniqueList(genreIds).size()) {
            throw new IllegalArgumentException("Для переданных иденетефикаторов жанров, существуют дубликаты");
        }
        final List<Genre> genres = genreService.findByIdList(genreIds);
        final List<Long> fakeGenreIds = fakeGenreIds(genreIds, genres);
        if (fakeGenreIds.size() > 0) {
            throw new GenreNotFoundLibraryException(
                    format("Для следующих переданных иденетефикаторов жанров { '%s' }, данные не найдены", String.join("', '", listToStringArray(fakeGenreIds)))
            );
        }
    }

    private void checkValidAuthorIds(List<Long> authorIds) {
        if (authorIds.size() != listToUniqueList(authorIds).size()) {
            throw new IllegalArgumentException("Для переданных иденетефикаторов авторов, существуют дубликаты");
        }
        final List<Author> authors = authorService.findByIdList(authorIds);
        final List<Long> fakeAuthorIds = fakeAuthorIds(authorIds, authors);
        if (fakeAuthorIds.size() > 0) {
            throw new GenreNotFoundLibraryException(
                    format("Для следующих переданных иденетефикаторов авторов { '%s' }, данные не найдены", String.join("', '", listToStringArray(fakeAuthorIds)))
            );
        }
    }
}