package ru.otus.homework.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.dto.BookDTO;
import ru.otus.homework.helper.BookHelper;
import ru.otus.homework.service.BookService;
import ru.otus.homework.service.PrintService;

import static java.lang.Long.parseLong;
import static org.springframework.shell.standard.ShellOption.NONE;

@RequiredArgsConstructor
@ShellCommandGroup("Book commands")
@ShellComponent
public class ShellBookCommands {
    private final PrintService printService;
    private final BookService bookService;

    @ShellMethod(key = {"book-list", "bl"}, value = "Show list of books")
    public void printBookList() {
        try {
            printService.printBookList();
        } catch (Exception e) {
            printService.printMessage(e.getMessage());
        }
    }

    @ShellMethod(key = {"book-info", "bi"}, value = "Show book info by id")
    public void printBookInfo(@ShellOption(value = "--id", help = "Identifier of book", defaultValue = "0") String id) {
        try {
            printService.printBookInfo(id);
        } catch (Exception e) {
            printService.printMessage(e.getMessage());
        }
    }

    @ShellMethod(key = {"book-create", "bc"}, value = "Book create")
    public void bookCreate(@ShellOption(value = "--t", help = "Title of book", defaultValue = NONE) String title,
                           @ShellOption(value = "--d", help = "Release date of book dd.mm.yyyy", defaultValue = NONE) String releaseDate,
                           @ShellOption(value = "--aid", help = "Identifiers of authors of book", defaultValue = "") String[] authorIds,
                           @ShellOption(value = "--gid", help = "Identifiers of genres of book", defaultValue = "") String[] genreIds) {
        try {
            BookDTO bookDTO = BookHelper.paramsToBookDtoForCreate(title, releaseDate, authorIds, genreIds);
            bookService.create(bookDTO);
        } catch (Exception e) {
            printService.printMessage(e.getMessage());
        }
    }

    @ShellMethod(key = {"book-update", "bu"}, value = "Book update")
    public void bookUpdate(@ShellOption(value = "--id", help = "Identifier of book for update", defaultValue = NONE) String id,
                           @ShellOption(value = "--t", help = "Title of book", defaultValue = NONE) String title,
                           @ShellOption(value = "--d", help = "Release date of book dd.mm.yyyy", defaultValue = NONE) String releaseDate,
                           @ShellOption(value = "--aid", help = "Identifiers of authors of book", defaultValue = "") String[] authorIds,
                           @ShellOption(value = "--gid", help = "Identifiers of genres of book", defaultValue = "") String[] genreIds) {
        try {
            BookDTO bookDTO = BookHelper.paramsToBookDtoForUpdate(id, title, releaseDate, authorIds, genreIds);
            bookService.update(bookDTO);
        } catch (Exception e) {
            printService.printMessage(e.getMessage());
        }
    }

    @ShellMethod(key = {"book-delete", "bd"}, value = "Book delete")
    public void bookDelete(@ShellOption(value = "--id", help = "Identifier of book for deletion", defaultValue = NONE) String id) {
        try {
            bookService.deleteById(parseLong(id));
        } catch (Exception e) {
            printService.printMessage(e.getMessage());
        }
    }
}
