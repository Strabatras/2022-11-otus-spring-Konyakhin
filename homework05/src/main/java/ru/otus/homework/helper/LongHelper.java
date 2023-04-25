package ru.otus.homework.helper;

import java.util.List;
import java.util.stream.Collectors;

public class LongHelper {
    public static List<Long> listToUniqueList(List<Long> list) {
        return list.stream()
                .distinct()
                .collect(Collectors.toList());
    }

    public static String[] listToStringArray(List<Long> list) {
        return list.stream()
                .map(String::valueOf)
                .toArray(String[]::new);
    }
}