package ru.otus.homework.dao.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.homework.dao.BookDao;
import ru.otus.homework.dao.extractor.AuthorGroupedByBookIdExtractor;
import ru.otus.homework.dao.extractor.GenreGroupedByBookIdExtractor;
import ru.otus.homework.dao.mapper.BookMapper;
import ru.otus.homework.domain.Author;
import ru.otus.homework.domain.Book;
import ru.otus.homework.domain.Genre;
import ru.otus.homework.dto.BookDTO;

import java.sql.Types;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class BookDaoJdbc implements BookDao {
    private static final String SQL_FIND_BOOK_BY_ID
            = "SELECT b.ID, b.TITLE, b.RELEASE_DATE"
            + "  FROM BOOK AS b "
            + "  WHERE b.ID = :book_id";

    private static final String SQL_FIND_ALL_BOOKS
            = "SELECT b.ID, b.TITLE, b.RELEASE_DATE"
            + "  FROM BOOK AS b";

    private static final String SQL_FIND_BOOK_BY_AUTHOR_ID
            = "SELECT b.ID, b.TITLE, b.RELEASE_DATE"
            + "  FROM BOOK_AUTHOR  AS ba"
            + "  JOIN BOOK AS b ON b.ID = ba.BOOK_ID"
            + " WHERE ba.AUTHOR_ID = :author_id";

    private static final String SQL_FIND_BOOK_BY_GENRE_ID
            = "SELECT b.ID, b.TITLE, b.RELEASE_DATE"
            + "  FROM BOOK_GENRE bg"
            + "  JOIN BOOK AS b ON b.ID = bg.BOOK_ID"
            + " WHERE bg.GENRE_ID = :genre_id";

    private static final String SQL_FIND_ALL_BOOK_GENRES
            = "SELECT bg.BOOK_ID, g.ID, g.TITLE"
            + "  FROM BOOK_GENRE AS bg"
            + "  JOIN GENRE AS g ON g.ID = bg.GENRE_ID";

    private static final String SQL_FIND_ALL_BOOK_AUTHORS
            = "SELECT ba.BOOK_ID, a.ID, a.NAME, a.SURNAME"
            + "  FROM BOOK_AUTHOR AS ba"
            + "  JOIN AUTHOR AS a ON a.ID = ba.AUTHOR_ID";

    private static final String SQL_DELETE_BOOK_AUTHOR_BY_BOOK_ID
            = "DELETE FROM BOOK_AUTHOR AS ba WHERE ba.BOOK_ID = :book_id";

    private static final String SQL_DELETE_BOOK_GENRE_BY_BOOK_ID
            = "DELETE FROM BOOK_GENRE AS bg WHERE bg.BOOK_ID = :book_id";

    private static final String SQL_DELETE_BOOK_BY_ID
            = "DELETE FROM BOOK b WHERE b.ID = :book_id";

    private static final String SQL_UPDATE_BOOK_BY_ID
            = "UPDATE BOOK AS b"
            + "   SET b.TITLE = :title, b.RELEASE_DATE = :release_date"
            + " WHERE id = :book_id";


    private static final String SQL_CREATE_BOOK
            = "INSERT INTO BOOK ( TITLE, RELEASE_DATE ) VALUES ( :title, :release_date )";

    private static final String SQL_DELETE_BOOK_AUTHOR_BY_BOOK_ID_LIST
            = "DELETE FROM BOOK_AUTHOR AS ba WHERE ba.BOOK_ID = IN ( :book_ids )";

    private static final String SQL_CREATE_BOOK_AUTHOR
            = "INSERT INTO BOOK_AUTHOR ( BOOK_ID, AUTHOR_ID )"
            + " VALUES ( :book_id, :author_id )";

    private static final String SQL_CREATE_BOOK_GENRE
            = "INSERT INTO BOOK_GENRE ( BOOK_ID, GENRE_ID )"
            + " VALUES ( :book_id, :genre_id )";

    private final NamedParameterJdbcOperations namedJdbc;

    @Override
    public Optional<Book> findById(Long id) {
        try {
            Book book = namedJdbc.queryForObject(SQL_FIND_BOOK_BY_ID,
                    Map.of("book_id", id),
                    new BookMapper(genreGroupedByBookId(), authorGroupedByBookId()));
            return Optional.of(book);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Book> findAll() {
        return namedJdbc.query(SQL_FIND_ALL_BOOKS, new BookMapper(genreGroupedByBookId(), authorGroupedByBookId()));
    }

    @Override
    public List<Book> findByAuthorId(Long id) {
        return namedJdbc.query(SQL_FIND_BOOK_BY_AUTHOR_ID,
                Map.of("author_id", id),
                new BookMapper(genreGroupedByBookId(), authorGroupedByBookId()));
    }

    @Override
    public List<Book> findByGenreId(Long id) {
        return namedJdbc.query(SQL_FIND_BOOK_BY_GENRE_ID,
                Map.of("genre_id", id),
                new BookMapper(genreGroupedByBookId(), authorGroupedByBookId()));
    }

    @Override
    public void deleteBookAuthorByBookId(Long id){
        namedJdbc.update(SQL_DELETE_BOOK_AUTHOR_BY_BOOK_ID,
                Map.of("book_id", id));
    }

    @Override
    public void deleteBookGenreByBookId(Long id){
        namedJdbc.update(SQL_DELETE_BOOK_GENRE_BY_BOOK_ID,
                Map.of("book_id", id));
    }
    @Override
    public void deleteById(Long id) {
        deleteBookAuthorByBookId(id);
        deleteBookGenreByBookId(id);
        namedJdbc.update(SQL_DELETE_BOOK_BY_ID,
                Map.of("book_id", id));
    }

    @Override
    public void update(BookDTO bookDTO){
        Map<String, Object> params = Map.of(
                "title", bookDTO.getTitle(),
                "release_date", bookDTO.getReleaseDate(),
                "book_id", bookDTO.getId()
        );
        namedJdbc.update(SQL_UPDATE_BOOK_BY_ID, params);
    }

    @Override
    public void create(BookDTO bookDTO) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String, Object> params = Map.of(
                "title", bookDTO.getTitle(),
                "release_date", bookDTO.getReleaseDate()
        );
        namedJdbc.update(SQL_CREATE_BOOK, new MapSqlParameterSource(params), keyHolder);
        bookDTO.setId(keyHolder.getKey().longValue());
    }

    @Override
    public void createBookAuthorBatch(Long bookId, List<Long> authorIds){
        var params = authorIds.stream()
                .map(authorId -> new MapSqlParameterSource()
                        .addValue("book_id", bookId, Types.NUMERIC)
                        .addValue("author_id", authorId, Types.NUMERIC))
                .toArray(MapSqlParameterSource[]::new);
        namedJdbc.batchUpdate(SQL_CREATE_BOOK_AUTHOR, params);
    }

    @Override
    public void createBookGenreBatch(Long bookId, List<Long> genreIds){
        var params = genreIds.stream()
                .map(genreId -> new MapSqlParameterSource()
                        .addValue("book_id", bookId, Types.NUMERIC)
                        .addValue("genre_id", genreId, Types.NUMERIC))
                .toArray(MapSqlParameterSource[]::new);
        namedJdbc.batchUpdate(SQL_CREATE_BOOK_GENRE, params);
    }

    private Map<Long, List<Genre>> genreGroupedByBookId() {
        return namedJdbc.query(SQL_FIND_ALL_BOOK_GENRES, new GenreGroupedByBookIdExtractor());
    }

    private Map<Long, List<Author>> authorGroupedByBookId() {
        return namedJdbc.query(SQL_FIND_ALL_BOOK_AUTHORS, new AuthorGroupedByBookIdExtractor());
    }
}