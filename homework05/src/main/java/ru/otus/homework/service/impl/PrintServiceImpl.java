package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.domain.Author;
import ru.otus.homework.domain.Book;
import ru.otus.homework.domain.Genre;
import ru.otus.homework.exception.AuthorNotFoundLibraryException;
import ru.otus.homework.exception.BookNotFoundLibraryException;
import ru.otus.homework.exception.GenreNotFoundLibraryException;
import ru.otus.homework.exception.LibraryRuntimeException;
import ru.otus.homework.service.AuthorService;
import ru.otus.homework.service.BookService;
import ru.otus.homework.service.GenreService;
import ru.otus.homework.service.OutputService;
import ru.otus.homework.service.PrintService;
import ru.otus.homework.util.AuthorUtil;
import ru.otus.homework.util.BookUtil;
import ru.otus.homework.util.GenreUtil;

import java.util.List;

import static java.lang.String.format;
import static ru.otus.homework.helper.StringHelper.stringToLong;

@RequiredArgsConstructor
@Service
public class PrintServiceImpl implements PrintService {
    private static final String SOMETHING_WENT_WRONG = "Что-то пошло не так";
    private final AuthorUtil authorUtil;
    private final BookUtil bookUtil;
    private final GenreUtil genreUtil;
    private final OutputService outputService;
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Override
    public void printAuthorList() {
        try {
            AuthorUtil authorUtil = new AuthorUtil();
            List<Author> authors = authorService.findAll();
            for (Author author : authors) {
                outputService.outputString(authorUtil.toStringInfo(author));
            }
        } catch (Exception e) {
            //to-do добавить логирование
            throw new LibraryRuntimeException(SOMETHING_WENT_WRONG, e);
        }
    }

    @Override
    public void printAuthorInfo(String id) {
        try {
            Long authorId = stringToLong(id);
            Author author = authorService.findById(authorId);
            List<Book> books = bookService.findByAuthorId(authorId);
            List<Genre> genres = genreService.findByAuthorId(authorId);
            outputService.outputString(authorUtil.toStringInfo(author));
            outputService.outputString("  Book's  : " + bookUtil.listToStringInfo(books));
            outputService.outputString("  Genre's : " + genreUtil.listToStringInfo(genres));
        } catch (NumberFormatException e) {
            //to-do добавить логирование
            throw new IllegalArgumentException("Идентификатор автора должен быть числом", e);
        } catch (AuthorNotFoundLibraryException e) {
            //to-do добавить логирование
            throw new AuthorNotFoundLibraryException(format("Автора с идентификатором {%s} не существует", id), e);
        } catch (Exception e) {
            //to-do добавить логирование
            throw new LibraryRuntimeException(SOMETHING_WENT_WRONG, e);
        }
    }

    @Override
    public void printGenreList() {
        try {
            List<Genre> genres = genreService.findAll();
            for (Genre genre : genres) {
                outputService.outputString(genreUtil.toStringInfo(genre));
            }
        } catch (Exception e) {
            //to-do добавить логирование
            throw new LibraryRuntimeException(SOMETHING_WENT_WRONG, e);
        }
    }

    @Override
    public void printGenreInfo(String id) {
        try {
            Long genreId = stringToLong(id);
            Genre genre = genreService.findById(genreId);
            List<Book> books = bookService.findByGenreId(genreId);
            List<Author> authors = authorService.findByGenreId(genreId);
            outputService.outputString(genreUtil.toStringInfo(genre));
            outputService.outputString("  Book's   : " + bookUtil.listToStringInfo(books));
            outputService.outputString("  Author's : " + authorUtil.listToStringInfo(authors));
        } catch (NumberFormatException e) {
            //to-do добавить логирование
            throw new IllegalArgumentException("Идентификатор жанра должен быть числом", e);
        } catch (GenreNotFoundLibraryException e) {
            //to-do добавить логирование
            throw new GenreNotFoundLibraryException(format("Жанра с идентификатором {%s} не существует", id), e);
        } catch (Exception e) {
            //to-do добавить логирование
            throw new LibraryRuntimeException(SOMETHING_WENT_WRONG, e);
        }
    }

    @Override
    public void printBookList() {
        try {
            List<Book> books = bookService.findAll();
            for (Book book : books) {
                outputService.outputString(bookUtil.toStringInfo(book));
            }
        } catch (Exception e) {
            //to-do добавить логирование
            throw new LibraryRuntimeException(SOMETHING_WENT_WRONG, e);
        }
    }

    @Override
    public void printBookInfo(String id) {
        try {
            Long bookId = stringToLong(id);
            Book book = bookService.findById(bookId);
            outputService.outputString(bookUtil.toStringInfo(book));
            outputService.outputString("  Author's : " + authorUtil.listToStringInfo(book.getAuthors()));
            outputService.outputString("  Genre's  : " + genreUtil.listToStringInfo(book.getGenres()));
        } catch (NumberFormatException e) {
            //to-do добавить логирование
            throw new IllegalArgumentException("Идентификатор книги должен быть числом", e);
        } catch (BookNotFoundLibraryException e) {
            //to-do добавить логирование
            throw new BookNotFoundLibraryException(format("Книги с идентификатором {%s} не существует", id), e);
        } catch (Exception e) {
            //to-do добавить логирование
            throw new LibraryRuntimeException(SOMETHING_WENT_WRONG, e);
        }
    }

    @Override
    public void printMessage(String message) {
        outputService.outputString(message);
    }
}