package com.javarush.task.jdk13.task23.task2304;

/* 
Напряги извилины!
*/

public class Solution {
    private String name;

    Solution(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    private void sout() {
        /*  Создание анонимного класса-наследника от Solution.
            Дочерний объект содержит собственную private переменную суперкласса name,
            но она для него не доступна.
            Доступ можно получить только через унаследованный getName() метод,
            но он также private, а значит не наследуется и нет возможности его вызвать.
            При этом приватный метод родителя getName (как и переменная name) доступны,
            т.к. анонимный класс находится внутри класса Solution.
            Поэтому при вызове getName() возвращает переменную name родительского класса Solution,
            Решение:
            При расширении модификатора доступа getName(), метод унаследуется и станет доступен на дочернем объекте,
            а при вызове вернёт собственную private переменную суперкласса дочернего объекта. ಠ_ಠ */

        new Solution("The Darkside Hacker") {
            void printName() {
                System.out.println(getName());
            }
        }.printName();
    }

    public static void main(String[] args) {
        new Solution("Риша").sout();
    }
}
