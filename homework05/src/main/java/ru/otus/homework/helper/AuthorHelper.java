package ru.otus.homework.helper;

import ru.otus.homework.domain.Author;
import ru.otus.homework.domain.Genre;

import java.util.List;
import java.util.stream.Collectors;

public class AuthorHelper {
    public static List<Long> fakeAuthorIds(List<Long> authorIds, List<Author> authors){
        return authorIds.stream()
                .filter(authorId -> authors.stream().noneMatch(author -> author.getId().equals(authorId)))
                .collect(Collectors.toList());
    }
}
