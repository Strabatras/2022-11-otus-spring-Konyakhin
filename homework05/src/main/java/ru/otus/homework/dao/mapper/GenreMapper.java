package ru.otus.homework.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.homework.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreMapper implements RowMapper<Genre> {
    @Override
    public Genre mapRow(ResultSet rs, int i) throws SQLException {
        Long id = rs.getLong("ID");
        String title = rs.getString("TITLE");
        return new Genre(id, title);
    }
}
