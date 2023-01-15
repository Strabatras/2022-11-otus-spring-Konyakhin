package ru.otus.homework.exception;

public class IOQuizException extends QuizRuntimeException {
    public IOQuizException() {
    }

    public IOQuizException(String message) {
        super(message);
    }

    public IOQuizException(String message, Throwable cause) {
        super(message, cause);
    }
}
