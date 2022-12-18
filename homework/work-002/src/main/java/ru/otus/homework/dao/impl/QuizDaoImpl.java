package ru.otus.homework.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.homework.dao.QuizDao;
import ru.otus.homework.domain.Quiz;
import ru.otus.homework.util.DataReader;
import ru.otus.homework.util.RowPreparation;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class QuizDaoImpl implements QuizDao {
    private final DataReader dataReader;

    public List<Quiz> getQuizzes() throws IOException {
        var lines = dataReader.readLines();
        return lines.stream()
                .filter(RowPreparation::isNotEmptyRowOrElseThrowException)
                .map(RowPreparation::rowToQuiz)
                .collect(Collectors.toList());
    }
}