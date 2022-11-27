package ru.otus.homework.service.impl;

import lombok.AllArgsConstructor;
import ru.otus.homework.dao.QuizDao;
import ru.otus.homework.domain.Quiz;
import ru.otus.homework.service.QuizService;

import java.util.List;

@AllArgsConstructor
public class QuizServiceImpl implements QuizService {
    private final QuizDao quizDao;

    @Override
    public List<Quiz> quizzes() {
        return quizDao.quizzes();
    }
}