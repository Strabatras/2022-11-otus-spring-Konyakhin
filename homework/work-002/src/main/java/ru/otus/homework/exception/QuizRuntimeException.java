package ru.otus.homework.exception;

public class QuizRuntimeException extends RuntimeException {
    public QuizRuntimeException() {
    }

    public QuizRuntimeException(String message) {
        super(message);
    }

    public QuizRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}