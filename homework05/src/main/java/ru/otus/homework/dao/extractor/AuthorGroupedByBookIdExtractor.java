package ru.otus.homework.dao.extractor;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import ru.otus.homework.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuthorGroupedByBookIdExtractor implements ResultSetExtractor<Map<Long, List<Author>>> {
    @Override
    public Map<Long, List<Author>> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Long, List<Author>> authors = new HashMap<>();
        while (rs.next()) {
            Long bookId = rs.getLong("BOOK_ID");
            if (!authors.containsKey(bookId)) {
                authors.put(bookId, new ArrayList<>());
            }

            Long id = rs.getLong("ID");
            String name = rs.getString("NAME");
            String surname = rs.getString("SURNAME");

            Author author = new Author(id, name, surname);
            authors.get(bookId).add(author);
        }
        return authors;
    }
}