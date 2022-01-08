package com.javarush.task.pro.task11.task1101;

/* 
Солнечная система — наш дом
*/

public class Solution {

    public static void main(String[] args) {

        System.out.println("Человечество живет в Солнечной системе.");
        System.out.println(String.format("Ее возраст около %d лет.", SolarSystem.age));
        System.out.println(String.format("В Солнечной системе %d известных планет.", SolarSystem.planetsCount));
        System.out.println(String.format("Как и большинство звездных систем, состоит из %d звезды.", SolarSystem.starsCount));
        System.out.println(String.format("Звезды по имени %s.", SolarSystem.starName));
        System.out.println(String.format("Расстояние к центру галактики составляет %d световых лет.", SolarSystem.galacticCenterDistance));
        System.out.println("Каждый обитатель Солнечной системы должен знать эту информацию!");

    }
}
