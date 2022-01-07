package com.javarush.task.pro.task09.task0914;

/* 
Обновление пути
*/

public class Solution {
    public static void main(String[] args) {
        String path = "/usr/java/jdk1.8";

        String jdk13 = "jdk-13";
        System.out.println(changePath(path, jdk13));
    }

    public static String changePath(String path, String jdk) {
        int firstIndexJDK = path.indexOf("jdk");
        int lastIndexJDK =  path.indexOf("/", firstIndexJDK);
        if (lastIndexJDK < 0) {
            lastIndexJDK = path.length();
        }

        String oldJDK = path.substring(firstIndexJDK, lastIndexJDK);
        return path.replace(oldJDK, jdk);
    }
}
