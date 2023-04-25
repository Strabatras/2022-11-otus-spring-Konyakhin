package ru.otus.homework.shell;

import lombok.RequiredArgsConstructor;
import org.h2.tools.Console;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.sql.SQLException;

@RequiredArgsConstructor
@ShellCommandGroup("H2 commands")
@ShellComponent
public class ShellH2Commands {
    @ShellMethod(key = {"h2-admin", "h2"}, value = "Show H2 administration console")
    public void console() {
        try {
            Console.main();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}