package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.dao.QuizDao;
import ru.otus.homework.domain.Quiz;
import ru.otus.homework.exception.FileNotFoundQuizException;
import ru.otus.homework.exception.FileReadLineQuizException;
import ru.otus.homework.exception.IOQuizException;
import ru.otus.homework.exception.QuizException;
import ru.otus.homework.service.QuizService;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class QuizServiceImpl implements QuizService {
    private final QuizDao quizDao;

    @Override
    public List<Quiz> getQuizzes() throws QuizException {
        List<Quiz> quizzes = null;
        try {
            quizzes = quizDao.getQuizzes();
        } catch (IllegalArgumentException e) {
            throw new FileNotFoundQuizException(e.getMessage());
        } catch (IOException e) {
            throw new IOQuizException(e.getMessage());
        } catch (RuntimeException e) {
            throw new FileReadLineQuizException(e.getMessage());
        }
        return quizzes;
    }
}