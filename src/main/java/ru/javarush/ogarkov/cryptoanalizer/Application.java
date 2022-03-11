package ru.javarush.ogarkov.cryptoanalizer;

import ru.javarush.ogarkov.cryptoanalizer.controllers.MainController;
import ru.javarush.ogarkov.cryptoanalizer.entity.Result;
import ru.javarush.ogarkov.cryptoanalizer.exceptions.AppException;

import java.util.Arrays;

public class Application {
    private final MainController mainController;
    public Application() {
        mainController = new MainController();  // когда собирается приложение - собираем mainController
    }

    public Result run(String[] args) {
        if (args.length>0){
            String action = args[0];
            String[] parameters = Arrays.copyOfRange(args, 1, args.length);
            Result result = mainController.doAction(action, parameters);
        }
        throw new AppException();
    }
}
