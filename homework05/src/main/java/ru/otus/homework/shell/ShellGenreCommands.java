package ru.otus.homework.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.service.PrintService;

import static org.springframework.shell.standard.ShellOption.NONE;

@RequiredArgsConstructor
@ShellCommandGroup("Genre commands")
@ShellComponent
public class ShellGenreCommands {
    private final PrintService printService;

    @ShellMethod(key = {"genre-list", "gl"}, value = "Show list of genres")
    public void printGenreList() {
        try {
            printService.printGenreList();
        } catch (Exception e) {
            printService.printMessage(e.getMessage());
        }
    }

    @ShellMethod(key = {"genre-info", "gi"}, value = "Show genre info by id")
    public void printGenreInfo(@ShellOption(value = "--id", help = "Identifier of genre", defaultValue = NONE) String id) {
        try {
            printService.printGenreInfo(id);
        } catch (Exception e) {
            printService.printMessage(e.getMessage());
        }
    }
}