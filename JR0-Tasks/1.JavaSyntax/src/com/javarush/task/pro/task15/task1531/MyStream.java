package com.javarush.task.pro.task15.task1531;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class MyStream extends ByteArrayInputStream {
    public MyStream(byte[] buf) {
        super(buf);
    }

    @Override
    public void close() throws IOException {
        System.out.println("Метод close() вызван!");
        super.close();
    }
}
