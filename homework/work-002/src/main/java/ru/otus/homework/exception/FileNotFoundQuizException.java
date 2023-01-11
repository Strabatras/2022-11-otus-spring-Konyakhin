package ru.otus.homework.exception;

public class FileNotFoundQuizException extends QuizRuntimeException {
    public FileNotFoundQuizException() {
        super();
    }

    public FileNotFoundQuizException(String message) {
        super(message);
    }

    public FileNotFoundQuizException(String message, Throwable cause) {
        super(message, cause);
    }
}