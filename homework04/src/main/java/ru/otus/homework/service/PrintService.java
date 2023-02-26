package ru.otus.homework.service;

public interface PrintService {
    void outputMessage(String message);
    void outputLocalizedMessage(String localizationCode);
    void outputLocalizedMessage(String localizationCode, Object ...localizationArgs);
}