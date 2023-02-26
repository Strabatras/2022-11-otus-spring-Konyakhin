package ru.otus.homework.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.homework.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorMapper implements RowMapper<Author> {
    @Override
    public Author mapRow(ResultSet rs, int i) throws SQLException {
        Long id = rs.getLong("ID");
        String name = rs.getString("NAME");
        String surname = rs.getString("SURNAME");
        return new Author(id, name, surname);
    }
}
