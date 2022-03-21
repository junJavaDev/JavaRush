package ru.javarush.ogarkov.cryptoanalizer.constants;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static final String russian = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    private static final String english = "abcdefghijklmnopqrstuvwxyz";
    private static final String punctuation = " .,“’\"'\n-:!?";
    private static final String cypher = "0123456789";
    private static final String symbols = "☮`~@#$%^&*()_=+[]{};|/<>№\\\t\r—«»– °…„";
    public static final String ALPHABET_LOWER_CASE_STRING = russian + punctuation;
    public static final String ALPHABET_STRING = ALPHABET_LOWER_CASE_STRING + russian.toUpperCase() + english + english.toUpperCase() + cypher + symbols;

    public static final Map<Character, Integer> ALPHABET_LOWER_CASE = new HashMap<>();
    public static final Map<Character, Integer> ALPHABET = new HashMap<>();
    public static final String TXT_FOLDER = System.getProperty("user.dir") +
            File.separator +
            "text" +
            File.separator;


    static {
        for (int i = 0; i < ALPHABET_LOWER_CASE_STRING.length(); i++) {
            ALPHABET_LOWER_CASE.put(ALPHABET_LOWER_CASE_STRING.charAt(i), i);
            ALPHABET.put(ALPHABET_LOWER_CASE_STRING.charAt(i), i);
        }
        for (int i = ALPHABET_LOWER_CASE_STRING.length(); i < ALPHABET_STRING.length(); i++) {
            ALPHABET.put(ALPHABET_STRING.charAt(i), i);
        }
    }

}
