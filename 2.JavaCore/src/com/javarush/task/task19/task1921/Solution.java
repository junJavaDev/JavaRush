package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/* 
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws IOException, ParseException {
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {

            ArrayList<String> list = new ArrayList<>();

            while (reader.ready()) {
                list.add(reader.readLine());
            }

            SimpleDateFormat format = new SimpleDateFormat("d M yyyy");
            Date date;
            for (String man : list
                 ) {
                int index;
                String[] result = man.split("\\s(?=\\d)");
                String strDate = result[1] + " " + result[2] + " " + result[3];
                date = format.parse(strDate);
                PEOPLE.add(new Person(result[0], date));
            }
        }

    }
}
