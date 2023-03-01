package ru.otus.homework.service;

public interface PrintService {
    void printAuthorList();

    void printAuthorInfo(String id);

    void printGenreList();

    void printGenreInfo(String id);

    void printBookList();

    void printBookInfo(String id);

    void printMessage(String message);
}