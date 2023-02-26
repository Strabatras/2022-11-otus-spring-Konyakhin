package ru.otus.homework.exception;

public class GenreNotFoundLibraryException extends LibraryRuntimeException {
    public GenreNotFoundLibraryException() {
        super();
    }

    public GenreNotFoundLibraryException(String message) {
        super(message);
    }

    public GenreNotFoundLibraryException(String message, Throwable cause) {
        super(message, cause);
    }
}