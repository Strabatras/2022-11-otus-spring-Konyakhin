package ru.otus.homework.util;

public interface Localization {
    String getMessage(String code, Object[] args);
    String getMessage(String code);
}