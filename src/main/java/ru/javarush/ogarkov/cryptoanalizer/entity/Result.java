package ru.javarush.ogarkov.cryptoanalizer.entity;

public record Result(String message, ResultCode resultCode) {

    @Override
    public String toString() {
        return String.format("%s\nResult: %s", message, resultCode);
    }
}
