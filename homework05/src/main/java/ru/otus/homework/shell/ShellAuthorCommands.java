package ru.otus.homework.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.service.PrintService;

import static org.springframework.shell.standard.ShellOption.NONE;

@RequiredArgsConstructor
@ShellCommandGroup("Author commands")
@ShellComponent
public class ShellAuthorCommands {
    private final PrintService printService;

    @ShellMethod(key = {"author-list", "al"}, value = "Show list of authors")
    public void printAuthorList() {
        try {
            printService.printAuthorList();
        } catch (Exception e) {
            printService.printMessage(e.getMessage());
        }
    }

    @ShellMethod(key = {"author-info", "ai"}, value = "Show author info by id")
    public void printAuthorInfo(@ShellOption(value = "--id", help = "Identifier of author", defaultValue = NONE) String id) {
        try {
            printService.printAuthorInfo(id);
        } catch (Exception e) {
            printService.printMessage(e.getMessage());
        }
    }
}