package ru.otus.homework.service;

public interface PrintService {
    void printAuthorList();

    void printAuthorInfo(Long id);

    void printGenreList();

    void printGenreInfo(Long id);

    void printBookList();

    void printBookInfo(String id);

    void printMessage(String message);
}