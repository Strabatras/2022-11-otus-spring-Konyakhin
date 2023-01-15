package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.dao.QuizDao;
import ru.otus.homework.domain.Quiz;
import ru.otus.homework.service.QuizService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QuizServiceImpl implements QuizService {
    private final QuizDao quizDao;

    @Override
    public List<Quiz> getQuizzes() {
        return quizDao.getQuizzes();
    }
}