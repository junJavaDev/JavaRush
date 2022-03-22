package ru.javarush.ogarkov.cryptoanalizer.entity;

import java.util.HashMap;
import java.util.Map;

public class TextStatistics {
    private final Map<Character, Integer> symbolsStatistics = new HashMap<>();
    private final Map<QuadChar, Integer> quadCharsStatistics = new HashMap<>();

    public void initializeSymbolsStatistics(String alphabetString) {
        for (int i = 0; i < alphabetString.length(); i++) {
            symbolsStatistics.put(alphabetString.charAt(i), 0);
        }
    }

    public void addData(char first, char second, char third, char fourth, Map<Character, Integer> alphabet) {
        if (alphabet.containsKey(fourth)) {
            addSymbolData(fourth);
            if (alphabet.containsKey(third) && alphabet.containsKey(second) && alphabet.containsKey(first)) {
                addQuadCharData(new QuadChar(first, second, third, fourth));
            }
        }
    }

    public void addStartData(char first, char second, char third, Map<Character, Integer> alphabet) {
        if (alphabet.containsKey(first)) {
            addSymbolData(first);
        }
        if (alphabet.containsKey(second)) {
            addSymbolData(second);
        }
        if (alphabet.containsKey(third)) {
            addSymbolData(third);
        }
    }

    private void addSymbolData(Character key) {
        if (symbolsStatistics.containsKey(key)) {
            symbolsStatistics.put(key, symbolsStatistics.get(key) + 1);
        } else
            symbolsStatistics.put(key, 1);
    }

    private void addQuadCharData(QuadChar key) {
        if (quadCharsStatistics.containsKey(key)) {
            quadCharsStatistics.put(key, quadCharsStatistics.get(key) + 1);
        } else quadCharsStatistics.put(key, 1);
    }

    public Map<Character, Integer> getSymbolsStatistics() {
        return symbolsStatistics;
    }

    public Map<QuadChar, Integer> getQuadCharsStatistics() {
        return quadCharsStatistics;
    }

}
