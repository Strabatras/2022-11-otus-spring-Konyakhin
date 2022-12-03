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
        var lines = dataReader.readLines();
        return lines.stream()
                .filter(line -> line.stream().findFirst().orElse("").trim().length() > 0)
                .map(line -> {
                    var name = line.stream().findFirst().get();
                    var answers = line.stream()
                            .skip(1)
                            .map(n -> new QuizAnswer(n, false)).collect(Collectors.toList());
                    return new Quiz(name, answers);
                }).collect(Collectors.toList());
    }
}