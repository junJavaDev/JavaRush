package com.javarush.task.task13.task1326;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/* 
Сортировка четных чисел из файла
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
       BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(reader.readLine())));
       List<Integer> nabor = new ArrayList<>();
       String line;
       while ((line = input.readLine()) != null) {
        if (line.equals("")) {
            continue;
        }
       nabor.add(Integer.parseInt(line));
       }
        for (int i:nabor
        ) {
            System.out.println(i);
        }
//        for (int i = 0; i < nabor.size(); i++) {
//            for (int j = i; j < nabor.size()-1; j++) {
//                if (nabor.get(j) > nabor.get(j+1)) {
//                    int l = nabor.get(j);
//                    nabor.set(j, nabor.get(j+1));
//                    nabor.set(j+1, l);
//            }
//        }
//        }


    }
}
