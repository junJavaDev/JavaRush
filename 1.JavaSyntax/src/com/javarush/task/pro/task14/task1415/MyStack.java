package com.javarush.task.pro.task14.task1415;

import java.util.ArrayList;
import java.util.List;

/* 
Стек в домашних условиях
*/

public class MyStack {

    private final List<String> storage = new ArrayList<>();
    ArrayList<String> storageArrayList = (ArrayList<String>)storage;

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        System.out.println(myStack.storage == myStack.storageArrayList);
    }

    public void push(String s) {
        storage.add(0, s);
        storageArrayList.ensureCapacity(342);
    }



    public String pop() {
        return storage.remove(0);

    }

    public String peek() {
        return storage.get(0);
    }

    public boolean empty() {
        return storage.isEmpty();
    }

    public int search(String s) {
        return storage.indexOf(s);
    }
}
