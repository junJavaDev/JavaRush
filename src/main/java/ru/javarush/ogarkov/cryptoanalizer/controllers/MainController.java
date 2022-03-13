package ru.javarush.ogarkov.cryptoanalizer.controllers;

import ru.javarush.ogarkov.cryptoanalizer.commands.*;
import ru.javarush.ogarkov.cryptoanalizer.entity.Result;
import ru.javarush.ogarkov.cryptoanalizer.exceptions.AppException;

public class MainController {

    public Result doAction(String someAction, String[] parameters) {
        Action action = switch (someAction) {
            case "encode" -> new Encoder();
            case "decode" -> new Decoder();
            case "bruteforce" -> new Bruteforcer();
            case "analyze" -> new Analyzer();
            default -> throw new AppException();
        };

        return action.execute(parameters);
    }



}
