package ru.otus.homework.dao.impl;

import lombok.RequiredArgsConstructor;
import ru.otus.homework.dao.QuizDao;
import ru.otus.homework.util.DataReader;

import java.util.List;

@RequiredArgsConstructor
public class QuizDaoImpl implements QuizDao {
    private final DataReader dataReader;

    public List<List<String>> getQuizData() {
        return dataReader.readLines();
    }
}