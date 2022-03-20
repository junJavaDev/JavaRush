package ru.javarush.ogarkov.cryptoanalizer.commands;

import ru.javarush.ogarkov.cryptoanalizer.constants.Constants;
import ru.javarush.ogarkov.cryptoanalizer.constants.Results;
import ru.javarush.ogarkov.cryptoanalizer.entity.*;
import ru.javarush.ogarkov.cryptoanalizer.entity.statistic.*;

import java.io.*;
import java.util.*;
import java.util.List;

public class Analyzer implements Action {

    TextStatistics encodedFileStatistics;
    TextStatistics exampleFileStatistics;
    List<Character> encodedSymbolPriority;
    List<Character> exampleSymbolPriority;
    List<QuadChar> encodedQuadCharPriority;
    List<QuadChar> exampleQuadCharPriority;
    Map<Character, Character> relationMap;

    @Override
    public Result execute(String[] parameters) {


        String encodedFile = parameters[0];
        String exampleFile = parameters[2];
        String decodedFile = parameters[1];

        encodedFileStatistics = getFileStatistic(encodedFile);
        exampleFileStatistics = getFileStatistic(exampleFile);

        encodedSymbolPriority = getSymbolsPriority(encodedFileStatistics.getSymbolsStatistics());
        exampleSymbolPriority = getSymbolsPriority(exampleFileStatistics.getSymbolsStatistics());
        encodedQuadCharPriority = getQuadCharPriority(encodedFileStatistics.getQuadCharsStatistics());
        exampleQuadCharPriority = getQuadCharPriority(exampleFileStatistics.getQuadCharsStatistics());

        relationMap = getRelationMap(encodedSymbolPriority, exampleSymbolPriority);

        tryImproveDecoding(70, 15, 1);
        tryImproveDecoding(70, 15, 2);
        tryImproveDecoding(70, 15, 3);
        tryImproveDecoding(60, 10, 4);

        char[] encodedFragment = getEncodedFragment(encodedFile);
        char[] decodedFragment = decodeFragment(encodedFragment);
        Editor editor = new Editor();
        editor.createJFrame();

        String replacingSymbols = editor.edit(String.valueOf(decodedFragment));
        while (!replacingSymbols.equals("complete")) {
            replaceSymbols(replacingSymbols.toCharArray());
            decodedFragment = decodeFragment(encodedFragment);
            replacingSymbols = editor.edit(String.valueOf(decodedFragment));
        }

        writeFile(encodedFile, decodedFile);
        printLists(encodedSymbolPriority, exampleSymbolPriority);

        return new Result(Results.FALSE);
    }

    private char[] decodeFragment(char[] encodedFragment) {
        char[] decodedFragment = new char[encodedFragment.length];
        for (int i = 0; i < encodedFragment.length; i++) {
            char encodedSymbol = Character.toLowerCase(encodedFragment[i]);
            if (relationMap.containsKey(encodedSymbol)) {
                decodedFragment[i] = relationMap.get(encodedSymbol);
            }
        }
        return decodedFragment;
    }

    private void writeFile(String encodedFile, String decodedFile) {
        try (FileReader fileReader = new FileReader(encodedFile); FileWriter fileWriter = new FileWriter(decodedFile)) {
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

    private void tryImproveDecoding(int quadCharsQuantity, int quadCharNeighbors, int deviation) {
        if (quadCharNeighbors < encodedQuadCharPriority.size() && quadCharsQuantity + quadCharNeighbors < exampleQuadCharPriority.size()) {
            for (int i = 0; i < quadCharsQuantity; i++) {
                for (int j = i >= quadCharNeighbors ? i - quadCharNeighbors : 0; j < i + quadCharNeighbors; j++) {
                    QuadChar encodedQuadChar = encodedQuadCharPriority.get(i);
                    QuadChar exampleQuadChar = exampleQuadCharPriority.get(j);
                    QuadChar decodedQuadChar = getDecodedQuadChar(encodedQuadChar);
                    int mismatchIndex = decodedQuadChar.getSingleMismatch(exampleQuadChar);

                    if (mismatchIndex > -1) {
                        char expectedChar = exampleQuadChar.getValue()[mismatchIndex];
                        char mismatchEncoded = encodedQuadChar.getValue()[mismatchIndex];
                        int indexMismatchEncoded = encodedSymbolPriority.indexOf(mismatchEncoded);
                        int indexNeighbor;

                        if ((indexNeighbor = indexMismatchEncoded - deviation) >= 0) {
                            replaceOnMatch(indexMismatchEncoded, indexNeighbor, expectedChar);
                        }
                        if ((indexNeighbor = indexMismatchEncoded + deviation) < encodedSymbolPriority.size()) {
                            replaceOnMatch(indexMismatchEncoded, indexNeighbor, expectedChar);
                        }
                        relationMap = getRelationMap(encodedSymbolPriority, exampleSymbolPriority);
                    }
                }
            }
        }
    }

    private QuadChar getDecodedQuadChar(QuadChar quadChar) {
        return new QuadChar(
                relationMap.get(quadChar.getFirstSymbol()),
                relationMap.get(quadChar.getSecondSymbol()),
                relationMap.get(quadChar.getThirdSymbol()),
                relationMap.get(quadChar.getFourthSymbol()));
    }

    private void replaceOnMatch(int indexMismatchEncoded, int indexNeighbor, char expectedChar) {
        char neighbor = encodedSymbolPriority.get(indexNeighbor);
        if (relationMap.get(neighbor) == expectedChar) {
            char mismatchEncoded = encodedSymbolPriority.get(indexMismatchEncoded);
            encodedSymbolPriority.set(indexMismatchEncoded, neighbor);
            encodedSymbolPriority.set(indexNeighbor, mismatchEncoded);
        }
    }

    private void replaceSymbols(char[] replacingSymbols) {
        if (replacingSymbols.length == 2 && Constants.SHORT_ALPHABET.containsKey(replacingSymbols[0]) && Constants.SHORT_ALPHABET.containsKey(replacingSymbols[1])) {
            int firstIndex = exampleSymbolPriority.indexOf(replacingSymbols[0]);
            int secondIndex = exampleSymbolPriority.indexOf(replacingSymbols[1]);
            char temp = encodedSymbolPriority.get(firstIndex);
            encodedSymbolPriority.set(firstIndex, encodedSymbolPriority.get(secondIndex));
            encodedSymbolPriority.set(secondIndex, temp);
            relationMap = getRelationMap(encodedSymbolPriority, exampleSymbolPriority);
        }
    }

    private List<Character> getSymbolsPriority(Map<Character, Integer> statistics) {
        Comparator<Map.Entry<Character, Integer>> sortByValues = Comparator.comparingInt(Map.Entry::getValue);
        List<Map.Entry<Character, Integer>> entryPriority = new ArrayList<>(statistics.entrySet());
        entryPriority.sort(sortByValues.reversed());
        List<Character> symbolsPriority = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : entryPriority) {
            symbolsPriority.add(entry.getKey());
        }
        return symbolsPriority;
    }

    private List<QuadChar> getQuadCharPriority(Map<QuadChar, Integer> statistics) {
        Comparator<Map.Entry<QuadChar, Integer>> sortByValues = Comparator.comparingInt(Map.Entry::getValue);
        List<Map.Entry<QuadChar, Integer>> entryPriority = new ArrayList<>(statistics.entrySet());
        entryPriority.sort(sortByValues.reversed());
        List<QuadChar> quadCharPriority = new ArrayList<>();
        for (Map.Entry<QuadChar, Integer> entry : entryPriority) {
            quadCharPriority.add(entry.getKey());
        }
        return quadCharPriority;
    }

    private TextStatistics getFileStatistic(String fileName) {
        TextStatistics fileStatistics = new TextStatistics();
        fileStatistics.initializeSymbolsStatistics();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.ready()) {
                char[] buf = new char[100000];
                int len = reader.read(buf);
                fileStatistics.addStartData(Character.toLowerCase(buf[0]), Character.toLowerCase(buf[1]), Character.toLowerCase(buf[2]));
                for (int i = 3; i < len; i++) {
                    fileStatistics.addData(Character.toLowerCase(buf[i - 3]), Character.toLowerCase(buf[i - 2]), Character.toLowerCase(buf[i - 1]), Character.toLowerCase(buf[i]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileStatistics;
    }

    private char[] getEncodedFragment(String fileName) {
        char[] encodedFragment = new char[33000];
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            char[] buf = new char[33000];
            int len = reader.read(buf);
            encodedFragment = Arrays.copyOfRange(buf, 0, len);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encodedFragment;
    }

    private Map<Character, Character> getRelationMap(List<Character> encodedPriority, List<Character> examplePriority) {
        //получить карту соответствий
        Map<Character, Character> relationMap = new HashMap<>();
        int length = Math.min(encodedPriority.size(), examplePriority.size());
        for (int i = 0; i < length; i++) {
            Character encoded = encodedPriority.get(i);
            Character example = examplePriority.get(i);
            relationMap.put(encoded, example);
        }
        return relationMap;
    }

    private void printLists(List first, List second) {
        int length = Math.min(first.size(), second.size());
        int mismatch = 0;
        for (int i = 0; i < length; i++) {
            if (!first.get(i).equals(second.get(i))) {
                mismatch++;
            }

            System.out.printf("[%s] = [%s]\n", first.get(i), second.get(i));
        }
        System.out.println(mismatch);

    }
}

