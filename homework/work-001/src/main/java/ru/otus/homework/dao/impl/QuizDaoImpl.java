package ru.otus.homework.dao.impl;

import lombok.RequiredArgsConstructor;
import ru.otus.homework.dao.QuizDao;
import ru.otus.homework.domain.Quiz;
import ru.otus.homework.domain.QuizAnswer;
import ru.otus.homework.util.DataReader;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class QuizDaoImpl implements QuizDao {
    private final DataReader dataReader;

    public List<Quiz> quizzes() {
        var data = dataReader.readLines();
        return data.stream()
                .filter(row -> !row.stream().findFirst().get().trim().isEmpty())
                .map(row -> {
                    var name = row.stream().findFirst().get();
                    var answers = row.stream()
                            .skip(1)
                            .map(n -> new QuizAnswer(n)).collect(Collectors.toList());
                    return new Quiz(name, answers);
                }).collect(Collectors.toList());
    }
}