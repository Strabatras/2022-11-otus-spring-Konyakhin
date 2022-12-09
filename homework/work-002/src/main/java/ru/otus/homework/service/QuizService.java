package ru.otus.homework.service;

import ru.otus.homework.domain.Quiz;
import ru.otus.homework.exception.QuizException;

import java.util.List;

public interface QuizService {
    List<Quiz> getQuizzes() throws QuizException;
}