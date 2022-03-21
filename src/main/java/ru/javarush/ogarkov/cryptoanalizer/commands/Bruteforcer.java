package ru.javarush.ogarkov.cryptoanalizer.commands;
import ru.javarush.ogarkov.cryptoanalizer.entity.ResultCode;
import ru.javarush.ogarkov.cryptoanalizer.entity.Result;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Bruteforcer implements Action {
    private final String alphabetString;
    private final Map<Character, Integer> alphabet;
    int[] matches;
    Set<String> dictionary;
    Map<Character, Character> relationMap;
    String encodedFile;
    String decodedFile;

    public Bruteforcer(String alphabetString, Map<Character, Integer> alphabet) {
        this.alphabetString = alphabetString;
        this.alphabet = alphabet;
    }

    @Override
    public Result execute(String[] parameters) {
        matches = new int[alphabet.size()];
        Long start = System.currentTimeMillis();

        initDictionary();
        encodedFile = parameters[0];
        decodedFile = parameters[1];

        for (int i = 0; i < alphabet.size(); i++) {
            int match = 0;
            relationMap = getRelationMap(i);
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(encodedFile))) {
                while (bufferedReader.ready()) {
                    String line;
                    char[] buf = new char[100000];
                    int len = bufferedReader.read(buf);
                    for (int j = 0; j < len - 4; j++) {
                        if (Character.isSpaceChar(relationMap.get(buf[j]))) {
                            char first = decodeChar(buf[j + 1]);
                            char second = decodeChar(buf[j + 2]);
                            char third = decodeChar(buf[j + 3]);
                            if (Character.isAlphabetic(first) && Character.isAlphabetic(second) && Character.isAlphabetic(third)) {
                                line = "" + first + second + third;
                                j += 3;
                                if (dictionary.contains(line)) match++;
                                char fourth = decodeChar(buf[j + 1]);
                                if (Character.isAlphabetic(fourth)) {
                                    line = "" + first + second + third + fourth;
                                    j += 1;
                                    if (dictionary.contains(line)) match++;
                                }
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            matches[i] = match;
//            System.out.println("match with key " + i + " = " + matches[i]);
        }
        int key = getKey(matches);


        System.out.println("Possible key found: " + key);
        if (key == 0) {
            return new Result("File doesn`t need to be decoded!", ResultCode.ERROR);
        }
        relationMap = getRelationMap(key);
        writeFile(encodedFile, decodedFile);
        Long end = System.currentTimeMillis();
        System.out.printf("Bruteforce time :%d millis\n", (end - start));
        return new Result("successfully", ResultCode.BRUTEFORCED);
    }

    private int getKey(int[] matches) {
        int key = 0;
        int maxMatch = matches[0];
        for (int i = 1; i < matches.length; i++) {
            if (maxMatch < matches[i]) {
                maxMatch = matches[i];
                key = i;
            }
        }
        return key;
    }

    private char decodeChar(char ch) {
        return Character.toLowerCase(relationMap.get(ch));
    }

    private void initDictionary() {
        try {
            dictionary = new HashSet<>(Files.readAllLines(Path.of(Objects.requireNonNull(getClass().getClassLoader().getResource("ru.txt")).toURI())));
            dictionary.addAll(Files.readAllLines(Path.of(Objects.requireNonNull(getClass().getClassLoader().getResource("en.txt")).toURI())));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private Map<Character, Character> getRelationMap(int key) {
        //получить карту соответствий
        Map<Character, Character> relationMap = new HashMap<>();
        for (Character encoded : alphabet.keySet()) {
            int position = alphabet.get(encoded);
            int decodedPosition = (position - key) % alphabet.size();
            if (decodedPosition < 0) {
                decodedPosition += alphabet.size();
            }
            char decoded = alphabetString.charAt(decodedPosition);
            relationMap.put(encoded, decoded);
        }
        return relationMap;
    }

    private void writeFile(String encodedFile, String decodedFile) {
        try (FileReader fileReader = new FileReader(encodedFile);
             FileWriter fileWriter = new FileWriter(decodedFile)) {
            while (fileReader.ready()) {
                char c = Character.toLowerCase((char) fileReader.read());
                if (relationMap.containsKey(c)) {
                    fileWriter.write(relationMap.get(c));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
