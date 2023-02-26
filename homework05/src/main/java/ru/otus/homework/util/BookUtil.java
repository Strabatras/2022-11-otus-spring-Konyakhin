package ru.otus.homework.util;

import org.springframework.stereotype.Component;
import ru.otus.homework.domain.Author;
import ru.otus.homework.domain.Book;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.StringJoiner;

@Component
public class BookUtil {
    public String toStringInfo(Book book) {
        return "#" + book.getId() + " " + book.getTitle() + " " + new SimpleDateFormat("dd.MM.yyyy").format(book.getReleaseDate());
    }

    public String listToStringInfo(List<Book> books) {
        StringJoiner bookStringJoiner = new StringJoiner(", ");
        for (Book book : books) {
            bookStringJoiner.add(toStringInfo(book));
        }
        return bookStringJoiner.toString();
    }
}
