package ru.javarush.ogarkov.cryptoanalizer.constants;

import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static final String russian = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    private static final String english = "abcdefghijklmnopqrstuvwxyz";
    private static final String punctuation = " .,“’\"'\n-:!?";
    private static final String cypher = "0123456789";
    private static final String symbols = "`~@#$%^&*()_=+[]{};|/<>№\\\t\r—«»– °…„";
    public static final String SHORT_ALPHABET_STRING = russian + punctuation;
    public static final String ALPHABET_STRING = SHORT_ALPHABET_STRING + russian.toUpperCase() + english + english.toUpperCase() + cypher + symbols;

    public static final Map<Character, Integer> SHORT_ALPHABET = new HashMap<>();
    public static final Map<Character, Integer> ALPHABET = new HashMap<>();

    static {
        for (int i = 0; i < SHORT_ALPHABET_STRING.length(); i++) {
            SHORT_ALPHABET.put(SHORT_ALPHABET_STRING.charAt(i), i);
            ALPHABET.put(SHORT_ALPHABET_STRING.charAt(i), i);
        }
        for (int i = SHORT_ALPHABET_STRING.length(); i < ALPHABET_STRING.length(); i++) {
            ALPHABET.put(ALPHABET_STRING.charAt(i), i);
        }
    }

}
