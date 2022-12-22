package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import ru.otus.homework.dao.QuizDao;
import ru.otus.homework.domain.Quiz;
import ru.otus.homework.service.QuizService;
import ru.otus.homework.util.RowPreparation;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {
    private final QuizDao quizDao;

    @Override
    public List<Quiz> getQuizzes() {
        return quizDao.getQuizData()
                .stream()
                .map(RowPreparation::rowToQuiz)
                .collect(Collectors.toList());

    }
}