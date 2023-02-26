package ru.otus.homework.dao.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.homework.dao.AuthorDao;
import ru.otus.homework.dao.mapper.AuthorMapper;
import ru.otus.homework.dao.mapper.GenreMapper;
import ru.otus.homework.domain.Author;
import ru.otus.homework.domain.Genre;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class AuthorDaoJdbc implements AuthorDao {

    private static final String SQL_FIND_AUTHOR_BY_ID
            = "SELECT a.ID, a.NAME, a.SURNAME"
            + "  FROM AUTHOR AS a"
            + " WHERE a.ID = :author_id";

    private static final String SQL_FIND_AUTHORS_BY_ID_LIST
            = "SELECT a.ID, a.NAME, a.SURNAME"
            + "  FROM AUTHOR AS a"
            + " WHERE a.ID IN( :author_ids )";

    private static final String SQL_FIND_ALL_AUTHORS
            = "SELECT a.ID, a.NAME, a.SURNAME"
            + "  FROM AUTHOR AS a";

    private static final String SQL_FIND_AUTHOR_BY_GENRE_ID
            = "SELECT a.ID, a.NAME, a.SURNAME"
            + "  FROM ( SELECT ba.AUTHOR_ID AS AUTHOR_ID"
            + "           FROM BOOK_GENRE bg"
            + "           JOIN BOOK_AUTHOR ba ON ba.BOOK_ID = bg.BOOK_ID"
            + "          WHERE bg.GENRE_ID = :genre_id"
            + "          GROUP BY ba.AUTHOR_ID"
            + "       ) AS ba"
            + "  JOIN AUTHOR AS a ON a.ID = ba.AUTHOR_ID";

    private final NamedParameterJdbcOperations namedJdbc;

    @Override
    public Optional<Author> findById(Long id) {
        try {
            Author author = namedJdbc.queryForObject(SQL_FIND_AUTHOR_BY_ID,
                    Map.of("author_id", id),
                    new AuthorMapper());
            return Optional.of(author);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Author> findByIdList(List<Long> ids){
            return namedJdbc.query(SQL_FIND_AUTHORS_BY_ID_LIST,
                    Map.of("author_ids", ids),
                    new AuthorMapper());
    }

    @Override
    public List<Author> findAll() {
        return namedJdbc.query(SQL_FIND_ALL_AUTHORS, new AuthorMapper());
    }

    @Override
    public List<Author> findByGenreId(Long id) {
        return namedJdbc.query(SQL_FIND_AUTHOR_BY_GENRE_ID,
                Map.of("genre_id", id),
                new AuthorMapper());
    }
}