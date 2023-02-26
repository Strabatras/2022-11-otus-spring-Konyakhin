package ru.otus.homework.helper;

import ru.otus.homework.domain.Genre;

import java.util.List;
import java.util.stream.Collectors;

public class GenreHelper {
    public static List<Long> fakeGenreIds(List<Long> genreIds, List<Genre> genres){
        return genreIds.stream()
                .filter(genreId -> genres.stream().noneMatch(genre -> genre.getId().equals(genreId)))
                .collect(Collectors.toList());
    }
}
