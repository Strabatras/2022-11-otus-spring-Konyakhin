package ru.otus.homework.exception;

public class LineValidationQuizException extends QuizRuntimeException {
    public LineValidationQuizException() {
    }

    public LineValidationQuizException(String message) {
        super(message);
    }

    public LineValidationQuizException(String message, Throwable cause) {
        super(message, cause);
    }
}
