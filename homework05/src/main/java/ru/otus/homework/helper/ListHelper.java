package ru.otus.homework.helper;

import java.util.List;

public class ListHelper {
    public static <T> boolean isNotEmpty(List<T> list){
        return (null != list || list.size() > 0);
    }
}
