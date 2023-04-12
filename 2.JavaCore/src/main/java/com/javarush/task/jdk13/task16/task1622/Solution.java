package com.javarush.task.jdk13.task16.task1622;

/* 
Аэропорт
*/

public class Solution {
    public static volatile Runway RUNWAY = new Runway();   // взлетная полоса

    public static void main(String[] args) throws InterruptedException {
        Plane plane1 = new Plane("Самолет #1");
        Plane plane2 = new Plane("Самолет #2");
        Plane plane3 = new Plane("Самолет #3");
    }

    private static void waiting() {
        // напишите тут ваш код
        try {
            Thread.sleep(100);
        } catch (InterruptedException ignore) {
        }
    }

    private static void takingOff() {
        // исправь этот метод
        try {
            Thread.sleep(100);
        } catch (InterruptedException ignore) {
        }
    }

    public static class Plane extends Thread {
        public Plane(String name) {
            super(name); // изменение имени потока
            start();
        }

        public void run() {
            boolean isAlreadyTakenOff = false; // самолёт не взлетел
            while (!isAlreadyTakenOff) { // пока самолёт не взлетит
                if (RUNWAY.trySetTakingOffPlane(this)) {    // если взлетная полоса свободна, занимаем ее
                    System.out.println(getName() + " взлетает");
                    takingOff();// взлетает
                    System.out.println(getName() + " уже в небе");
                    isAlreadyTakenOff = true; // самолёт взлетел
                    RUNWAY.setTakingOffPlane(null); // очистить полосу
                } else if (!this.equals(RUNWAY.getTakingOffPlane())) {  // если взлетная полоса занята самолетом
                    System.out.println(getName() + " ожидает");
                    waiting(); // ожидает
                }
            }
        }
    }

    public static class Runway { // взлетная полоса
        private Thread t; // самолет взлётной полосы, он же Thread

        public Thread getTakingOffPlane() { // вернуть самолёт, который занимает полосу
            return t;
        }

        public void setTakingOffPlane(Thread t) { // метод для очистки полосы после взлёта, без проверок
            synchronized (this) {
                this.t = t;
            }
        }

        public boolean trySetTakingOffPlane(Thread t) { // установить самолёт, который занимает полосу, проверив что она пустая
            synchronized (this) {
                if (this.t == null) {
                    this.t = t;
                    return true;
                }
                return false;
            }
        }
    }
}

