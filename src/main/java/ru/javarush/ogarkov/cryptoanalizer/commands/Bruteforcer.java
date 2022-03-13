package ru.javarush.ogarkov.cryptoanalizer.commands;

import ru.javarush.ogarkov.cryptoanalizer.constants.Results;
import ru.javarush.ogarkov.cryptoanalizer.entity.Result;

public class Bruteforcer implements Action {
    @Override
    public Result execute(String[] parameters) {
        return new Result(Results.FALSE);
    }
}
