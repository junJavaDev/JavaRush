package ru.javarush.ogarkov.cryptoanalizer.constants;

public class Constants {
    private static final String rus = "ЙЦУКЕНГШЩЗХЪФЫВАПРОЛДЖЭЁЯЧСМИТЬБЮ";
    private static final String eng = "QWERTYUIOPASDFGHJKLZXCVBNM";
    private static final String cypher = "0123456789";
    private static final String punctuation = "`~!@#$%^&*()-_=+[]{};:'\"\\|/,<.>?№ ";
    public static final String ALPHABET = rus + eng + rus.toLowerCase() + eng.toLowerCase() + cypher + punctuation;
}
