package ru.otus.homework.service;

import ru.otus.homework.util.ShellQuizRunner;

public interface QuizRunnerService {
    void run();
    void runInShell(ShellQuizRunner shellQuizRunner);
    void runOutputStatisticInShell(ShellQuizRunner shellQuizRunner);
}