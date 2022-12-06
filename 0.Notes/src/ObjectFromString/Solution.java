package ObjectFromString;
//package com.javarush.task.pro.task15.task1531;

import java.io.*;
import java.util.Base64;

/* 
task1531
*/

public class Solution {

    public static void main(String[] args) throws ClassNotFoundException {
        try {
            String string = toString(new MyClass("Съешь ещё этих мягких французских булок."));
            System.out.printf("Объект в виде строки: %s\n", string);

            MyClass myClass = (MyClass) fromString(string);
            System.out.println("Восстановленный объект: " + myClass);

            System.out.println();
            fromString("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String toString(Serializable o) throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(o);
            return Base64.getEncoder().encodeToString(baos.toByteArray());
        }
    }

    public static Object fromString(String s) throws IOException, ClassNotFoundException {
        byte[] data = Base64.getDecoder().decode(s);

        try (MyStream ms = new MyStream(data);
             ObjectInputStream ois = new ObjectInputStream(ms)) {
            Object o = ois.readObject();
            return o;
        }
    }
}
