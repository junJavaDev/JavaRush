package ru.javarush.ogarkov.cryptoanalizer.entity.statistic;

import ru.javarush.ogarkov.cryptoanalizer.constants.Constants;

import java.util.HashMap;
import java.util.Map;

public class TextStatistics {
    private final Map<Character, Integer> symbolsStatistics = new HashMap<>();
    private final Map<QuadChar, Integer> quadCharsStatistics = new HashMap<>();

    public void initializeSymbolsStatistics() {
        for (int i = 0; i < Constants.SHORT_ALPHABET_STRING.length(); i++) {
            symbolsStatistics.put(Constants.SHORT_ALPHABET_STRING.charAt(i), 0);
        }
    }

    public void addData(char first, char second, char third, char fourth) {
        if (Constants.SHORT_ALPHABET.containsKey(fourth)) {
            addSymbolData(fourth);
            if (Constants.SHORT_ALPHABET.containsKey(third) && Constants.SHORT_ALPHABET.containsKey(second)) {
                if (Constants.SHORT_ALPHABET.containsKey(first)) {
                        addQuadCharData(new QuadChar(first, second, third, fourth));
                }
            }
        }
    }

    public void addStartData(char first, char second, char third) {
        if (Constants.SHORT_ALPHABET.containsKey(first)) {
            addSymbolData(first);
        }
        if (Constants.SHORT_ALPHABET.containsKey(second)) {
            addSymbolData(second);
        }
        if (Constants.SHORT_ALPHABET.containsKey(third)) {
            addSymbolData(third);
        }
    }

    private void addSymbolData(Character key) {
        symbolsStatistics.put(key, symbolsStatistics.get(key) + 1);
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
