package ru.otus.homework.service;

public interface QuizShellRunnerService {
    void authorizationRun(String name, String surname);
    void quizRun();
    void statisticRun();
}
