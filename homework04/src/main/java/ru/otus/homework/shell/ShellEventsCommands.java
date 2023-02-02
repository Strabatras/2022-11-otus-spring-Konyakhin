package ru.otus.homework.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.domain.Personality;
import ru.otus.homework.service.LocalizationService;
import ru.otus.homework.service.QuizRunnerService;
import ru.otus.homework.util.ShellQuizRunner;

import java.util.StringJoiner;

import static org.apache.commons.lang3.ObjectUtils.isEmpty;

@ShellComponent
@RequiredArgsConstructor
public class ShellEventsCommands {
    private final LocalizationService localizationService;
    private final QuizRunnerService quizRunnerService;
    private final ShellQuizRunner shellQuizRunner;

    @ShellMethod(key = {"authorization", "a"}, value = "Please enter your name and surname")
    public String authorization(@ShellOption(value = "--n", help = "Name", defaultValue = "") String name,
                                @ShellOption(value = "--s", help = "Surname", defaultValue = "") String surname) {
        var personality = new Personality(name, surname);
        shellQuizRunner.setPersonality(personality);
        StringJoiner outputMessage = new StringJoiner(". ");
        if (isEmpty(personality.getName())) {
            outputMessage.add(localizationService.getMessage("identify.name.cant.be.empty"));
        }
        if (isEmpty(personality.getSurname())) {
            outputMessage.add(localizationService.getMessage("identify.surname.cant.be.empty"));
        }
        if (outputMessage.length() == 0) {
            outputMessage.add(localizationService.getMessage("identify.you.can.answer.questions"));
        }
        return outputMessage.toString();
    }

    @ShellMethod(key = {"run", "r"}, value = "Answer the questions. Authorization required")
    public String quizRun() {
        if (isEmpty(shellQuizRunner.getPersonality())) {
            return localizationService.getMessage("identify.authorized.please");
        }
        quizRunnerService.runInShell(shellQuizRunner);
        return localizationService.getMessage("have.access.to.questions.statistics");
    }

    @ShellMethod(key = {"statistics", "s"}, value = "You can view statistics after answering the questions")
    public String statisticRun() {
        if (isEmpty(shellQuizRunner.getInterview())) {
            return localizationService.getMessage("please.answer.the.questions");
        }
        quizRunnerService.runOutputStatisticInShell(shellQuizRunner);
        return localizationService.getMessage("enter.exit.to.complete");
    }
}