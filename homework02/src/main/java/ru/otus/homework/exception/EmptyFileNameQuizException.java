package ru.otus.homework.exception;

public class EmptyFileNameQuizException  extends QuizRuntimeException {
    public EmptyFileNameQuizException() {
        super();
    }

    public EmptyFileNameQuizException(String message) {
        super(message);
    }

    public EmptyFileNameQuizException(String message, Throwable cause) {
        super(message, cause);
    }
}