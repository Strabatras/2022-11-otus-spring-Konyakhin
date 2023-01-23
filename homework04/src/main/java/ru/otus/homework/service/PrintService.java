package ru.otus.homework.service;

public interface PrintService {
    void outputLocalizedMessage(String localizationCode);
    void outputLocalizedMessage(String localizationCode, Object ...localizationArgs);
}