package ru.otus.homework.dao.extractor;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import ru.otus.homework.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenreGroupedByBookIdExtractor implements ResultSetExtractor<Map<Long, List<Genre>>> {
    @Override
    public Map<Long, List<Genre>> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Long, List<Genre>> genres = new HashMap<>();
        while (rs.next()) {
            Long bookId = rs.getLong("BOOK_ID");
            if (!genres.containsKey(bookId)) {
                genres.put(bookId, new ArrayList<>());
            }

            Long id = rs.getLong("ID");
            String title = rs.getString("TITLE");


            Genre genre = new Genre(id, title);
            genres.get(bookId).add(genre);
        }
        return genres;
    }
}