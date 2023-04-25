package ru.otus.homework.exception;

public class AuthorNotFoundLibraryException extends LibraryRuntimeException {
    public AuthorNotFoundLibraryException() {
        super();
    }

    public AuthorNotFoundLibraryException(String message) {
        super(message);
    }

    public AuthorNotFoundLibraryException(String message, Throwable cause) {
        super(message, cause);
    }
}