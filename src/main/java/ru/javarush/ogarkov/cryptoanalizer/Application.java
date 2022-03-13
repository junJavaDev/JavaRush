package ru.javarush.ogarkov.cryptoanalizer;

import ru.javarush.ogarkov.cryptoanalizer.constants.Results;
import ru.javarush.ogarkov.cryptoanalizer.controllers.MainController;
import ru.javarush.ogarkov.cryptoanalizer.entity.Result;

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
            return mainController.doAction(action, parameters);
        }
        return new Result(Results.FALSE);
//        throw new AppException();

    }
}
