package ru.otus.homework.dao;

import ru.otus.homework.domain.Quiz;

import java.util.List;

public interface QuizDao {
    List<Quiz> quizzes();
}