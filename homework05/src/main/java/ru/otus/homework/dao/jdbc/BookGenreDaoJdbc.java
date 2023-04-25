package ru.otus.homework.dao.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.homework.dao.BookGenreDao;

import java.sql.Types;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class BookGenreDaoJdbc implements BookGenreDao {
    private static final String SQL_CREATE_BOOK_GENRE
            = "INSERT INTO BOOK_GENRE ( BOOK_ID, GENRE_ID )"
            + " VALUES ( :book_id, :genre_id )";

    private static final String SQL_DELETE_BOOK_GENRE_BY_BOOK_ID
            = "DELETE FROM BOOK_GENRE AS bg WHERE bg.BOOK_ID = :book_id";

    private final NamedParameterJdbcOperations namedJdbc;

    @Override
    public void createBatch(Long bookId, List<Long> genreIds) {
        var params = genreIds.stream()
                .map(genreId -> new MapSqlParameterSource()
                        .addValue("book_id", bookId, Types.NUMERIC)
                        .addValue("genre_id", genreId, Types.NUMERIC))
                .toArray(MapSqlParameterSource[]::new);
        namedJdbc.batchUpdate(SQL_CREATE_BOOK_GENRE, params);
    }

    @Override
    public void deleteByBookId(Long id) {
        namedJdbc.update(SQL_DELETE_BOOK_GENRE_BY_BOOK_ID,
                Map.of("book_id", id));
    }
}