package ru.otus.homework.exception;

public class QuizException extends Exception {
    public QuizException(){}
    public QuizException(String message) {
        super(message);
    }
}