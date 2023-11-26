package com.javarush.task.pro.task13.task1326;

import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyQueue extends AbstractQueue<String> {

    private final List<String> values = new ArrayList<>();

    @Override
    public Iterator<String> iterator() {
        //напишите тут ваш код
        return values.iterator();
    }

    @Override
    public int size() {
        //напишите тут ваш код
        return values.size();
    }

    @Override
    public boolean offer(String o) {
        return values.add(o);
    }

    @Override
    public String poll() {
        return values.size() > 0 ? values.remove(0) : null;
    }

    @Override
    public String peek() {
        return values.size() > 0 ? values.get(0) : null;
    }
}
