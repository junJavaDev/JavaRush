package com.javarush.task.jdk13.task23.task2304;

/* 
Напряги извилины!
*/

public class Solution {
    private String name;
    Solution(String name) {
        this.name = name;
    }
    protected String getName() {
        return name;
    }
    private void sout() {

        new Solution("The Darkside Hacker") {
            // Создание анонимного класса-наследника от Solution.
            void printName() {
                System.out.println(getName());
            }
            }.printName();
        /*
            Объект этого анонимного класса-наследника содержит собственную private переменную суперкласса name
            со значением "The Darkside Hacker", инициализированную через родительский конструктор, содержит
            приватный метод getName() родительского класса, но они для него не доступны,
            т.к. они являются private переменными другого класса, хоть и родителя,
            при этом он находится внутри класса Solution, а значит видит его private переменную name со значением "Риша",
            также видит его приватный метод getName().
            Поэтому при вызове getName() анонимный класс вызывает приватный метод внешнего класса Solution, который видит,
            а тот в свою очередь возвращает приватную переменную name внешнего класса Solution,
            Решение:
            При расширении модификатора доступа getName(), метод станет доступен на дочернем объекте,
            вызовется свой собственный метод getName(), который вернёт собственную private переменную суперкласса,
            которую он содержит. ಠ_ಠ */

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
