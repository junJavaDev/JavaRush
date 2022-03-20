package ru.javarush.ogarkov.cryptoanalizer.commands;

import ru.javarush.ogarkov.cryptoanalizer.constants.Constants;
import ru.javarush.ogarkov.cryptoanalizer.constants.Results;
import ru.javarush.ogarkov.cryptoanalizer.entity.Result;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Bruteforcer implements Action {
    int[] matches = new int[Constants.ALPHABET.size()];
    Set<String> russianWords;

    @Override
    public Result execute(String[] parameters) {
        Long start = System.currentTimeMillis();
        try {
            russianWords = new HashSet<>(Files.readAllLines(Path.of(Objects.requireNonNull(getClass().getClassLoader().getResource("ru.txt")).toURI())));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        String inputFile = parameters[0];
        String outputFIle = parameters[1];
        int key = 0;
        for (int i = 0; i < Constants.ALPHABET.size(); i++) {
            int match = 0;
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile))) {
                while (bufferedReader.ready()) {
                    String line = bufferedReader.readLine();
                    //временный декодер, потом взять из класса декодер
                    char[] chars = line.toCharArray();
                    for (int j = 0; j < chars.length; j++) {
                        int position;
                        if (Constants.ALPHABET.containsKey(chars[j])) {
                            position = Constants.ALPHABET.get(chars[j]);
                            if (position >= 0) {
                                int toPosition = (position - i) % Constants.ALPHABET.size();
                                if (toPosition < 0) {
                                    toPosition += Constants.ALPHABET.size();
                                }
                                chars[j] = Constants.ALPHABET_STRING.charAt(toPosition);
                            }
                        }

                    }
                    line = String.valueOf(chars);
                    //конец декодера

                    String[] words = line.split("[^А-Яа-я]+");
                    for (String word : words) {
                        if (word.length() > 4) {
                            word = word.substring(0, 4);
                        }
                        if (word.length() > 2 && russianWords.contains(word.toLowerCase())) match++;
                    }
                }
            } catch (FileNotFoundException e) {
                return new Result(Results.FILE_NOT_FOUND, parameters);
            } catch (IOException e) {
                e.printStackTrace();
            }
            matches[i] = match;
        }
        int maxMatch = matches[0];
        for (int i = 1; i < matches.length; i++) {
            if (maxMatch < matches[i]) {
                maxMatch = matches[i];
                key = i;
            }

        }
        System.out.println("Possible key found: " + key);
        if (key == 0) {
            System.out.println("The file does not need to be decoded!");
            return new Result(Results.FALSE);
        }
        Long end = System.currentTimeMillis();
        System.out.printf("Затраченное время на взлом :\n%d миллисекунд\n", (end - start) );
        String[] parametersWithBruteForcedKey = {inputFile, outputFIle, String.valueOf(key)};
        return new Decoder().execute(parametersWithBruteForcedKey);
    }
}
