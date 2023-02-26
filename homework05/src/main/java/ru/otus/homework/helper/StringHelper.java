package ru.otus.homework.helper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringHelper {
    public static List<Long> arrayToDistinctLongList(String... str) {
        return Arrays.stream(str).distinct()
                .map(Long::parseLong)
                .collect(Collectors.toList());
    }
}
