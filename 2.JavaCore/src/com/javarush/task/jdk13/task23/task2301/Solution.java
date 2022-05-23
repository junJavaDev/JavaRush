package com.javarush.task.jdk13.task23.task2301;

/* 
Inner
*/

public class Solution {
    public InnerClass[] innerClasses = new InnerClass[2];

    public static void main(String[] args) {

    }

    public static Solution[] getTwoSolutions() {
        Solution first = new Solution();
        Solution second = new Solution();
        first.innerClasses[0] = new InnerClass();
        first.innerClasses[1] = new InnerClass();
        second.innerClasses[0] = new InnerClass();
        second.innerClasses[1] = new InnerClass();

        return new Solution[]{first, second};
    }

    public static class InnerClass {
    }
}
