package ru.javarush.ogarkov.cryptoanalizer;

import ru.javarush.ogarkov.cryptoanalizer.constants.Constants;
import ru.javarush.ogarkov.cryptoanalizer.entity.Result;

import java.util.Arrays;

public class ConsoleRunner {
    public static void main(String[] args) {

        Application application = new Application();
        Result result = application.run(args);//закинул args в метод run класса Application
        result.show();
    }
}
