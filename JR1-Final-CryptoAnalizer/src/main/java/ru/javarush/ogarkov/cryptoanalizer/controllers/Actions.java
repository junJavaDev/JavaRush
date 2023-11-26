package ru.javarush.ogarkov.cryptoanalizer.controllers;

import ru.javarush.ogarkov.cryptoanalizer.commands.*;
import ru.javarush.ogarkov.cryptoanalizer.constants.Constants;
import ru.javarush.ogarkov.cryptoanalizer.exceptions.AppException;

public enum Actions {
    ENCODE(new Encoder(Constants.ALPHABET_STRING, Constants.ALPHABET)),
    ENCODE_LC(new Encoder(Constants.ALPHABET_LOWER_CASE_STRING, Constants.ALPHABET_LOWER_CASE)),
    DECODE(new Decoder(Constants.ALPHABET_STRING, Constants.ALPHABET)),
    DECODE_LC(new Decoder(Constants.ALPHABET_LOWER_CASE_STRING, Constants.ALPHABET_LOWER_CASE)),
    BRUTEFORCE(new Bruteforcer(Constants.ALPHABET_STRING, Constants.ALPHABET)),
    BRUTEFORCE_LC(new Bruteforcer(Constants.ALPHABET_LOWER_CASE_STRING, Constants.ALPHABET_LOWER_CASE)),
    ANALYZE(new Analyzer(Constants.ALPHABET_LOWER_CASE_STRING, Constants.ALPHABET_LOWER_CASE));
    private final Action action;

    Actions(Action action) {
        this.action = action;
    }

    public static Action find(String actionName) {
        try {
            Actions value = Actions.valueOf(actionName.toUpperCase());
            return value.action;
        } catch (IllegalArgumentException e) {
            throw new AppException("not found " + actionName, e);
        }
    }
}
