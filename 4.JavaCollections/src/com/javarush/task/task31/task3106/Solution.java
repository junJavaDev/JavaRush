package com.javarush.task.task31.task3106;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Разархивируем файл
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        Path result = Paths.get(args[0]);

        ArrayList<String> partsOfFileNames = new ArrayList<>();
        for (int i = 1; i < args.length; i++) {
            partsOfFileNames.add(args[i]);
        }
        Collections.sort(partsOfFileNames);

        ArrayList<Byte> resultToBytes = new ArrayList<>();

        for (String filePart:partsOfFileNames
             ) {
            byte[] tempBytes = Files.readAllBytes(Paths.get(filePart));
            for (byte b:tempBytes
                 ) {
                resultToBytes.add(b);
            }
        }

        byte[] rez = new byte[resultToBytes.size()];
        for (int i = 0; i < resultToBytes.size(); i++) {
            rez[i] = resultToBytes.get(i);
        }
        Path tempResult = Files.createTempFile("", ".zip");
        Files.write(tempResult, rez);
        try (FileInputStream fis = new FileInputStream(tempResult.toString());
             ZipInputStream zipInputStream = new ZipInputStream(fis);
             FileOutputStream fos = new FileOutputStream(result.toString())) {
            for (int i = zipInputStream.read(); i != -1 ; i = zipInputStream.read()) {
            fos.write(i);
            }

        }
    }
}
