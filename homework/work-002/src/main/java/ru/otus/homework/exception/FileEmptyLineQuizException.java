package ru.otus.homework.exception;

public class FileEmptyLineQuizException extends QuizRuntimeException {
    public FileEmptyLineQuizException() {
    }

    public FileEmptyLineQuizException(String message) {
        super(message);
    }
}
