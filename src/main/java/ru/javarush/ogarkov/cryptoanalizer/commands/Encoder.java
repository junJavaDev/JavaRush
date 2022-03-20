package ru.javarush.ogarkov.cryptoanalizer.commands;

import ru.javarush.ogarkov.cryptoanalizer.constants.Constants;
import ru.javarush.ogarkov.cryptoanalizer.constants.Results;
import ru.javarush.ogarkov.cryptoanalizer.entity.Result;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Encoder implements Action {
    @Override
    public Result execute(String[] parameters) {
        int key;
        try {key = Integer.parseInt(parameters[2]);}
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Отсутствует ключ");
            return new Result (Results.INVALID_KEY, parameters);
        }
        try (FileReader fileReader = new FileReader(parameters[0]);
             FileWriter fileWriter = new FileWriter(parameters[1])) {
            while (fileReader.ready()) {
                int position = Constants.ALPHABET.get((char)fileReader.read());
                if (position >= 0) {
                    int toPosition = (position + key) % Constants.ALPHABET.size();
                    fileWriter.write(Constants.ALPHABET_STRING.charAt(toPosition));
                }
            }
        } catch (FileNotFoundException e) {
            return new Result(Results.FILE_NOT_FOUND, parameters);
        } catch (IOException e) {
            return new Result(Results.FALSE);
        }
        return new Result(Results.ENCODED, parameters);
    }
}
