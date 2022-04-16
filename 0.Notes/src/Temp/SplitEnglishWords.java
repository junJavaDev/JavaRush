package Temp;

import java.io.*;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

public class SplitEnglishWords {
    static String file1;
    static String file2;
    static Set<String> words = new TreeSet<>();

    public static void main(String[] args) throws IOException {
        file1 = "B:\\Temp\\en.txt";
        file2 = "B:\\Temp\\englishResult3.txt";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file1))) {
            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();
                if (line.length() == 4) {
                    words.add(line);
                }
            }
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file2))) {
            for (String word : words) {
                bufferedWriter.write(word);
                bufferedWriter.write('\n');
            }
        }
    }
}
