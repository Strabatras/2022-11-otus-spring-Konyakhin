package ru.otus.homework.helper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Long.parseLong;

public class StringHelper {
    public static List<Long> arrayToDistinctLongList(String... str) {
        return Arrays.stream(str).distinct()
                .map(Long::parseLong)
                .collect(Collectors.toList());
    }

    public static Long stringToLong(String str) {
        if (str == null || str.isEmpty()) {
            throw new NumberFormatException("Передан пустой параметр");
        }
        return parseLong(str);
    }
}
