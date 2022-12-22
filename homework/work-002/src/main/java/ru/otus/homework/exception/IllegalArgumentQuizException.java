package ru.otus.homework.exception;

public class IllegalArgumentQuizException extends QuizRuntimeException {
    public IllegalArgumentQuizException() {
    }

    public IllegalArgumentQuizException(String message) {
        super(message);
    }

    public IllegalArgumentQuizException(String message, Throwable cause) {
        super(message, cause);
    }
}
