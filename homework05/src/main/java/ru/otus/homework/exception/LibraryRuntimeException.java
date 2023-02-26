package ru.otus.homework.exception;

public class LibraryRuntimeException extends RuntimeException {
    public LibraryRuntimeException() {
        super();
    }

    public LibraryRuntimeException(String message) {
        super(message);
    }

    public LibraryRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}