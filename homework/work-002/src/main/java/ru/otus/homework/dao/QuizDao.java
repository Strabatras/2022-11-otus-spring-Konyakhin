package ru.otus.homework.dao;

import ru.otus.homework.domain.Quiz;

import java.io.IOException;
import java.util.List;

public interface QuizDao {
    List<Quiz> getQuizzes() throws IOException;
}