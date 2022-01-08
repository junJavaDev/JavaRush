package com.javarush.task.pro.task14.task1418;

public class ChildrenNotInitializedException extends RuntimeException {
    public ChildrenNotInitializedException(String message) {
        System.out.println(message);
    }
}
