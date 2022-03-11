package ru.javarush.ogarkov.cryptoanalizer.commands;

import ru.javarush.ogarkov.cryptoanalizer.entity.Result;

public interface Action {
    Result execute(String[] parameters);
}
