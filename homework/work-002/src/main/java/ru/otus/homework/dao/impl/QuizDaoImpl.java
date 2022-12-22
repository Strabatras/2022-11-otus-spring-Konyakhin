package ru.otus.homework.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.homework.dao.QuizDao;
import ru.otus.homework.util.DataReader;

import java.util.List;

@RequiredArgsConstructor
@Component
public class QuizDaoImpl implements QuizDao {
    private final DataReader dataReader;

    public List<List<String>> getQuizData() {
        return dataReader.readLines();
    }
}