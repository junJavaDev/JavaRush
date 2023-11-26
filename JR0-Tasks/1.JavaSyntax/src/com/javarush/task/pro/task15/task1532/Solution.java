package com.javarush.task.pro.task15.task1532;

/* 
task1532
*/

import java.io.*;

public class Solution {

    public static void main(String[] args) {
        System.out.println(encrypt("abcdefghi"));
    }

    public static ByteArrayOutputStream encrypt(String message) {
        byte[] bytes = message.getBytes();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i = 0; i < bytes.length / 2; i++) {
            byteArrayOutputStream.write(bytes[i]);
            byteArrayOutputStream.write(bytes[bytes.length - i - 1]);
        }
        if (bytes.length % 2 == 1) {
            byteArrayOutputStream.write(bytes[bytes.length / 2]);
        }
        return byteArrayOutputStream;
    }
}
