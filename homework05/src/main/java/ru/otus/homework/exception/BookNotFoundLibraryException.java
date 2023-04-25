package ru.otus.homework.exception;

public class BookNotFoundLibraryException extends LibraryRuntimeException {
    public BookNotFoundLibraryException() {
        super();
    }

    public BookNotFoundLibraryException(String message) {
        super(message);
    }

    public BookNotFoundLibraryException(String message, Throwable cause) {
        super(message, cause);
    }
}