package ru.otus.homework.dao.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.homework.dao.GenreDao;
import ru.otus.homework.dao.mapper.GenreMapper;
import ru.otus.homework.domain.Genre;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class GenreDaoJdbc implements GenreDao {
    private static final String SQL_FIND_GENRE_BY_ID
            = "SELECT g.ID, g.TITLE"
            + "  FROM GENRE AS g "
            + "  WHERE g.ID = :genre_id";

    private static final String SQL_FIND_GENRES_BY_ID_LIST
            = "SELECT g.ID, g.TITLE"
            + "  FROM GENRE AS g "
            + " WHERE g.ID IN ( :genre_ids )";

    private static final String SQL_FIND_ALL_GENRES
            = "SELECT g.ID, g.TITLE"
            + "  FROM GENRE AS g ";

    private static final String SQL_FIND_GENRE_BY_AUTHOR_ID
            = "SELECT g.ID, g.TITLE"
            + "  FROM ( SELECT bg.GENRE_ID AS GENRE_ID"
            + "           FROM BOOK_AUTHOR AS ba"
            + "           JOIN BOOK_GENRE AS bg ON bg.BOOK_ID = ba.BOOK_ID"
            + "          WHERE ba.AUTHOR_ID = :author_id"
            + "          GROUP BY bg.GENRE_ID"
            + "       ) AS bg"
            + "  JOIN GENRE AS g ON g.ID = bg.GENRE_ID";

    private final NamedParameterJdbcOperations namedJdbc;

    @Override
    public Optional<Genre> findById(Long id) {
        try {
            Genre genre = namedJdbc.queryForObject(SQL_FIND_GENRE_BY_ID,
                    Map.of("genre_id", id),
                    new GenreMapper());
            return Optional.of(genre);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Genre> findByIdList(List<Long> ids) {
        return namedJdbc.query(SQL_FIND_GENRES_BY_ID_LIST,
                Map.of("genre_ids", ids),
                new GenreMapper());
    }

    @Override
    public List<Genre> findAll() {
        return namedJdbc.query(SQL_FIND_ALL_GENRES, new GenreMapper());
    }

    @Override
    public List<Genre> findByAuthorId(Long id) {
        return namedJdbc.query(SQL_FIND_GENRE_BY_AUTHOR_ID,
                Map.of("author_id", id),
                new GenreMapper());
    }
}