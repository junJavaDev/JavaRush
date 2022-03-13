package ru.javarush.ogarkov.cryptoanalizer.constants;

import java.util.Arrays;

public class Constants {
    private static final String rus = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    private static final String eng = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String cypher = "0123456789";
    private static final String punctuation = "`~!@#$%^&*()-_=+[]{};:'|/,<.>?№ \"\\\r\n\t—«»– °…’„“";
    public static final char[] ALPHABET = (rus + eng + rus.toLowerCase() + eng.toLowerCase() + cypher + punctuation).toCharArray();
    static {
        Arrays.sort(ALPHABET);
    }
}
