package ru.otus.homework.exception;

public class FileNotFoundQuizException extends QuizException {
    public FileNotFoundQuizException() {
    }

    public FileNotFoundQuizException(String message) {
        super(message);
    }
}