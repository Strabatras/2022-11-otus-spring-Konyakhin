package ru.otus.homework.dao.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import ru.otus.homework.domain.Author;
import ru.otus.homework.domain.Book;
import ru.otus.homework.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class BookMapper implements RowMapper<Book> {
    private final Map<Long, List<Genre>> genres;
    private final Map<Long, List<Author>> authors;

    @Override
    public Book mapRow(ResultSet rs, int i) throws SQLException {
        Long id = rs.getLong("ID");
        String title = rs.getString("TITLE");
        Date releaseDate = rs.getDate("RELEASE_DATE");

        List<Genre> genreList = genres.containsKey(id) ? genres.get(id) : new ArrayList<>();
        List<Author> authorList = authors.containsKey(id) ? authors.get(id) : new ArrayList<>();

        return new Book(id, title, releaseDate, genreList, authorList);
    }
}
