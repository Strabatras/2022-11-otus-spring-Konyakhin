package ru.otus.homework.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.service.QuizShellRunnerService;

@ShellComponent
@RequiredArgsConstructor
public class ShellEventsCommands {
    private final QuizShellRunnerService quizShellRunnerService;

    @ShellMethod(key = {"authorization", "a"}, value = "Please enter your name and surname")
    public void authorization(@ShellOption(value = "--n", help = "Name", defaultValue = "") String name,
                              @ShellOption(value = "--s", help = "Surname", defaultValue = "") String surname) {
        quizShellRunnerService.authorizationRun(name, surname);
    }

    @ShellMethod(key = {"run", "r"}, value = "Answer the questions. Authorization required")
    public void quizRun() {
        quizShellRunnerService.quizRun();
    }

    @ShellMethod(key = {"statistics", "s"}, value = "You can view statistics after answering the questions")
    public void statisticRun() {
        quizShellRunnerService.statisticRun();
    }
}