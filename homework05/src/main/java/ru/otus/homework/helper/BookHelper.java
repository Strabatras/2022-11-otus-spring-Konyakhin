package ru.otus.homework.helper;

import ru.otus.homework.dto.BookDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import static java.lang.Long.parseLong;
import static org.apache.logging.log4j.util.Strings.isBlank;
import static ru.otus.homework.helper.StringHelper.arrayToDistinctLongList;

public class BookHelper {
    public static BookDTO paramsToBookDtoForCreate(String title, String releaseDate, String[] authorIds, String[] genreIds) {
        BookDTO bookDTO = bookDTO();
        titleToBookDTO(title, bookDTO);
        releaseDateToBookDTO(releaseDate, bookDTO);
        authorIdsToBookDTO(authorIds, bookDTO);
        genreIdsToBookDTO(genreIds, bookDTO);
        return bookDTO;
    }

    public static BookDTO paramsToBookDtoForUpdate(String id, String title, String releaseDate, String[] authorIds, String[] genreIds) {
        BookDTO bookDTO = bookDTO();
        idToBookDTO(id, bookDTO);
        if (title != null) {
            bookDTO.setTitle(title);
        }
        if (releaseDate != null) {
            releaseDateToBookDTO(releaseDate, bookDTO);
        }
        authorIdsToBookDTO(authorIds, bookDTO);
        genreIdsToBookDTO(genreIds, bookDTO);
        return bookDTO;
    }

    private static BookDTO bookDTO(){
        return new BookDTO();
    }

    private static void idToBookDTO(String id, BookDTO bookDTO) {
        try {
            bookDTO.setId(parseLong(id));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Идентификатор книги должен быть числом", e);
        }
    }

    private static void titleToBookDTO(String title, BookDTO bookDTO) {
        if (title == null || title.isBlank()){
            throw new IllegalArgumentException("Название книги не может быть пустым");
        }
        bookDTO.setTitle(title);
    }

    private static void releaseDateToBookDTO(String releaseDate, BookDTO bookDTO){
        if (releaseDate == null || releaseDate.isBlank()){
            throw new IllegalArgumentException("Дата выхода книги не может быть пустой");
        }
        try {
            bookDTO.setReleaseDate(new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).parse(releaseDate));
        } catch (ParseException e) {
            throw new IllegalArgumentException("Неверный формат даты выхода книги", e);
        }
    }

    private static void authorIdsToBookDTO(String[] authorIds, BookDTO bookDTO){
        if (authorIds.length > 0){
            try {
                bookDTO.setAuthorIds(arrayToDistinctLongList(authorIds));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Идентификаторы авторов должны быть числами", e);
            }
        }
    }

    private static void genreIdsToBookDTO(String[] genreIds, BookDTO bookDTO){
        if (genreIds.length > 0){
            try {
                bookDTO.setGenreIds(arrayToDistinctLongList(genreIds));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Идентификаторы жанров должны быть числами", e);
            }
        }
    }
}