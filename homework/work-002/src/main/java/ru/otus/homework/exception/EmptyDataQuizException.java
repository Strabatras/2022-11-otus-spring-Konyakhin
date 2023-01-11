package ru.otus.homework.exception;

public class EmptyDataQuizException extends QuizRuntimeException {
    public EmptyDataQuizException() {
        super();
    }

    public EmptyDataQuizException(String message) {
        super(message);
    }

    public EmptyDataQuizException(String message, Throwable cause) {
        super(message, cause);
    }
}