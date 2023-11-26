package ru.javarush.ogarkov.cryptoanalizer.controllers;

import ru.javarush.ogarkov.cryptoanalizer.commands.Action;
import ru.javarush.ogarkov.cryptoanalizer.entity.Result;

public class MainController {

    public Result doAction(String actionName, String[] parameters) {
        Action action = Actions.find(actionName);
        return action.execute(parameters);
    }



}
