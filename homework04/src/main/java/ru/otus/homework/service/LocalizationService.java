package ru.otus.homework.service;

public interface LocalizationService {
    String getMessage(String code, Object ...args);
    String getMessage(String code);
}