package ru.javarush.ogarkov.cryptoanalizer.commands;

import ru.javarush.ogarkov.cryptoanalizer.entity.ResultCode;
import ru.javarush.ogarkov.cryptoanalizer.entity.Result;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class Decoder implements Action{
    private final String alphabetString;
    private final Map<Character, Integer> alphabet;
    public Decoder(String alphabetString, Map<Character, Integer> alphabet) {
        this.alphabetString = alphabetString;
        this.alphabet = alphabet;
    }

    @Override
    public Result execute(String[] parameters) {
        int key;
        try {key = Integer.parseInt(parameters[2]);}
        catch (ArrayIndexOutOfBoundsException e) {
            return new Result("Something was wrong", ResultCode.ERROR);
        }
        try (FileReader fileReader = new FileReader(parameters[0]);
             FileWriter fileWriter = new FileWriter(parameters[1])) {
            while (fileReader.ready()) {
                int position = alphabet.get((char)fileReader.read());
                if (position >= 0) {
                    int toPosition = (position - key) % alphabet.size();
                    if (toPosition < 0) {
                        toPosition += alphabet.size();
                    }
                    fileWriter.write(alphabetString.charAt(toPosition));
                }
            }
        } catch (FileNotFoundException e) {
            return new Result("File not found", ResultCode.FILE_NOT_FOUND);
        } catch (IOException e) {
            return new Result("Something was wrong", ResultCode.ERROR);
        }
        return new Result("successfully", ResultCode.DECODED);
    }
}
