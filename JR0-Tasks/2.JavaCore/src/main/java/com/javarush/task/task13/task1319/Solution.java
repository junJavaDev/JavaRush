package com.javarush.task.task13.task1319;

import java.io.*;

/* 
Писатель в файл с консоли
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filePath = reader.readLine();

        BufferedWriter bWriter = new BufferedWriter(new FileWriter(filePath));
        String line;
        while (!(line = reader.readLine()).equals("exit")) {
            bWriter.write(line);
            bWriter.newLine();
            bWriter.flush();
        }
        bWriter.write(line);
        bWriter.flush();
        reader.close();
        bWriter.close();
    }
}
