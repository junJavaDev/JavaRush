package ru.javarush.ogarkov.cryptoanalizer.commands;

import ru.javarush.ogarkov.cryptoanalizer.constants.Constants;
import ru.javarush.ogarkov.cryptoanalizer.entity.ResultCode;
import ru.javarush.ogarkov.cryptoanalizer.entity.Result;
import ru.javarush.ogarkov.cryptoanalizer.exceptions.AppException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public record Encoder(String alphabetString,
                      Map<Character, Integer> alphabet) implements Action {

    @Override
    public Result execute(String[] parameters) {
        boolean isLowerCaseMode = (alphabet.size() == Constants.ALPHABET_LOWER_CASE.size());
        try (FileReader fileReader = new FileReader(parameters[0]);
             FileWriter fileWriter = new FileWriter(parameters[1])) {
            int key = Integer.parseInt(parameters[2]);
            if (key < 0) {
                return new Result("Invalid key", ResultCode.ERROR);
            }
            while (fileReader.ready()) {
                char symbol = (char) fileReader.read();
                if (isLowerCaseMode) {
                    symbol = Character.toLowerCase(symbol);
                }
                int position = -1;
                if (alphabet.containsKey(symbol)) {
                    position = alphabet.get(symbol);
                }
                if (position >= 0) {
                    int toPosition = (position + key) % alphabet.size();
                    fileWriter.write(alphabetString.charAt(toPosition));
                }
            }
        } catch (FileNotFoundException e) {
            return new Result("File not found", ResultCode.ERROR);
        } catch (NumberFormatException e) {
            return new Result("Invalid key", ResultCode.ERROR);
        } catch (ArrayIndexOutOfBoundsException e) {
            return new Result("Not enough parameters", ResultCode.ERROR);
        } catch (IOException e) {
            throw new AppException("IO Exception", e);
        }
        return new Result("Successfully", ResultCode.ENCODED);
    }
}
