package ru.otus.homework.dao.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.homework.dao.BookAuthorDao;

import java.sql.Types;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class BookAuthorDaoJdbc implements BookAuthorDao {
    private static final String SQL_CREATE_BOOK_AUTHOR
            = "INSERT INTO BOOK_AUTHOR ( BOOK_ID, AUTHOR_ID )"
            + " VALUES ( :book_id, :author_id )";

    private static final String SQL_DELETE_BOOK_AUTHOR_BY_BOOK_ID
            = "DELETE FROM BOOK_AUTHOR AS ba WHERE ba.BOOK_ID = :book_id";

    private final NamedParameterJdbcOperations namedJdbc;

    @Override
    public void createBatch(Long bookId, List<Long> authorIds) {
        var params = authorIds.stream()
                .map(authorId -> new MapSqlParameterSource()
                        .addValue("book_id", bookId, Types.NUMERIC)
                        .addValue("author_id", authorId, Types.NUMERIC))
                .toArray(MapSqlParameterSource[]::new);
        namedJdbc.batchUpdate(SQL_CREATE_BOOK_AUTHOR, params);
    }

    @Override
    public void deleteByBookId(Long id) {
        namedJdbc.update(SQL_DELETE_BOOK_AUTHOR_BY_BOOK_ID,
                Map.of("book_id", id));
    }
}