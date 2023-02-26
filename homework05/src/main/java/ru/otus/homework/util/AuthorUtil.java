package ru.otus.homework.util;

import org.springframework.stereotype.Component;
import ru.otus.homework.domain.Author;
import ru.otus.homework.domain.Book;

import java.util.List;
import java.util.StringJoiner;

@Component
public class AuthorUtil {

    public String toStringInfo(Author author) {
        return "#" + author.getId() + " " + author.getName() + " " + author.getSurname();
    }

    public String listToStringInfo(List<Author> authors) {
        StringJoiner authorStringJoiner = new StringJoiner(", ");
        for (Author author : authors) {
            authorStringJoiner.add(toStringInfo(author));
        }
        return authorStringJoiner.toString();
    }
}
