package ru.otus.homework.util;

import org.springframework.stereotype.Component;
import ru.otus.homework.domain.Genre;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Component
public class GenreUtil {
    public String toStringInfo(Genre genre) {
        return "#" + genre.getId() + " " + genre.getTitle();
    }

    public String listToStringInfo(List<Genre> genres) {
        StringJoiner authorStringJoiner = new StringJoiner(", ");
        for (Genre genre : genres) {
            authorStringJoiner.add(toStringInfo(genre));
        }
        return authorStringJoiner.toString();
    }

    public List<Long> listToIdList(List<Genre> genres) {
        return genres.stream()
                .map(Genre::getId)
                .collect(Collectors.toList());
    }
}