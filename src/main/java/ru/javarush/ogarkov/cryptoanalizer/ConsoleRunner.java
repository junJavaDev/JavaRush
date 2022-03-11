package ru.javarush.ogarkov.cryptoanalizer;

import ru.javarush.ogarkov.cryptoanalizer.entity.Result;

import java.util.Arrays;

public class ConsoleRunner {
    public static void main(String[] args) {

        Application application = new Application(); //закинул args в экземпляр класса Application
        Result result = application.run(args);
        System.out.println(result);

        System.out.println(Arrays.toString(args));
    }
}
