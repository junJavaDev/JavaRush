package ru.javarush.island.ogarkov.exception;

public class IslandException extends RuntimeException {
    public IslandException() {
    }

    public IslandException(Throwable cause) {
        super(cause);
    }
}
