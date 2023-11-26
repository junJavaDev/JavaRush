package ru.javarush.ogarkov.cryptoanalizer.commands;

import ru.javarush.ogarkov.cryptoanalizer.entity.QuadChar;
import ru.javarush.ogarkov.cryptoanalizer.entity.Result;
import ru.javarush.ogarkov.cryptoanalizer.entity.TextStatistics;
import ru.javarush.ogarkov.cryptoanalizer.exceptions.AppException;
import ru.javarush.ogarkov.cryptoanalizer.view.Editor;
import ru.javarush.ogarkov.cryptoanalizer.entity.ResultCode;

import java.io.*;
import java.util.*;
import java.util.List;

public class Analyzer implements Action {
    private final String alphabetString;
    private final Map<Character, Integer> alphabet;
    public Analyzer(String alphabetString, Map<Character, Integer> alphabet) {
        this.alphabetString = alphabetString;
        this.alphabet = alphabet;
    }
    TextStatistics encodedFileStatistics;
    TextStatistics exampleFileStatistics;
    List<Character> encodedSymbolPriority;
    List<Character> exampleSymbolPriority;
    List<QuadChar> encodedQuadCharPriority;
    List<QuadChar> exampleQuadCharPriority;
    Map<Character, Character> relationMap;
    String encodedFile;
    String exampleFile;
    String decodedFile;

    @Override
    public Result execute(String[] parameters) {
        try {

            encodedFile = parameters[0];
            exampleFile = parameters[1];
            decodedFile = parameters[2];

            encodedFileStatistics = getFileStatistic(encodedFile, true);
            exampleFileStatistics = getFileStatistic(exampleFile, false);

            encodedSymbolPriority = getSymbolsPriority(encodedFileStatistics.getSymbolsStatistics());
            exampleSymbolPriority = getSymbolsPriority(exampleFileStatistics.getSymbolsStatistics());
            encodedQuadCharPriority = getQuadCharPriority(encodedFileStatistics.getQuadCharsStatistics());
            exampleQuadCharPriority = getQuadCharPriority(exampleFileStatistics.getQuadCharsStatistics());

            relationMap = getRelationMap(encodedSymbolPriority, exampleSymbolPriority);

            tryImproveDecoding(60, 15, 1);
            tryImproveDecoding(59, 14, 2);

            char[] encodedFragment = getEncodedFragment(encodedFile);
            Editor editor = new Editor();
            editor.createJFrame();

            String replacingSymbols = editor.edit(String.valueOf(decodeFragment(encodedFragment)));
            while (!replacingSymbols.equals("complete")) {
                replaceSymbols(replacingSymbols.toCharArray());
                replacingSymbols = editor.edit(String.valueOf(decodeFragment(encodedFragment)));
            }

            writeFile(encodedFile, decodedFile);
//        printLists(encodedSymbolPriority, exampleSymbolPriority);     //включать при настройке анализатора
        } catch (FileNotFoundException e) {
            return new Result("File not found", ResultCode.ERROR);
        } catch (ArrayIndexOutOfBoundsException e) {
            return new Result("Not enough parameters", ResultCode.ERROR);
        } catch (IOException e) {
            throw new AppException("IO Exception", e);
        }
        return new Result("Sucessfully", ResultCode.ANALYZED);
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

    private void writeFile(String encodedFile, String decodedFile) throws IOException {
        try (FileReader fileReader = new FileReader(encodedFile);
             FileWriter fileWriter = new FileWriter(decodedFile)) {
            while (fileReader.ready()) {
                char c = Character.toLowerCase((char) fileReader.read());
                if (relationMap.containsKey(c)) {
                    fileWriter.write(relationMap.get(c));
                }
            }
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
        if (replacingSymbols.length == 2 && alphabet.containsKey(replacingSymbols[0]) && alphabet.containsKey(replacingSymbols[1])) {
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

    private TextStatistics getFileStatistic(String fileName, boolean expandable) throws IOException {
        TextStatistics fileStatistics = new TextStatistics();
        fileStatistics.initializeSymbolsStatistics(alphabetString);
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.ready()) {
                char[] buf = new char[100000];
                int len = reader.read(buf);
                    expandAlphabet(buf[0], expandable);
                    expandAlphabet(buf[1], expandable);
                    expandAlphabet(buf[2], expandable);
                fileStatistics.addStartData(Character.toLowerCase(buf[0]), Character.toLowerCase(buf[1]), Character.toLowerCase(buf[2]), alphabet);
                for (int i = 3; i < len; i++) {
                    expandAlphabet(buf[i], expandable);
                    fileStatistics.addData(Character.toLowerCase(buf[i - 3]), Character.toLowerCase(buf[i - 2]), Character.toLowerCase(buf[i - 1]), Character.toLowerCase(buf[i]), alphabet);
                }
            }
        }
        return fileStatistics;
    }

    private void expandAlphabet(char symbol, boolean expandable) {
        if (expandable) {
            if (!alphabet.containsKey(symbol)) {
                alphabet.put(symbol, alphabet.size());
            }
        }

    }

    private char[] getEncodedFragment(String fileName) throws IOException {
        char[] encodedFragment;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            char[] buf = new char[50000];
            int len = reader.read(buf);
            encodedFragment = Arrays.copyOfRange(buf, 0, len);
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

    private void printLists(List<Character> first, List<Character> second) {
        /*
        Служебный метод, необходим для отображения найденных пар символов и расчета эффективности анализатора.
        Для расчета эффективности анализатора, нужно вместо зашифрованного файла
        подать уже расшифрованный, эффективность будет показана количеством несоответствий
        */
        int length = Math.min(first.size(), second.size());
        int mismatch = 0;
        for (int i = 0; i < length; i++) {
            if (!first.get(i).equals(second.get(i))) {
                mismatch++;
            }
            System.out.printf("[%s] = [%s]\n", first.get(i), second.get(i));
        }
        System.out.println("Количество несоответствий = " + mismatch);
    }
}

