package com.javarush.task.jdk13.task16.task1615;

import java.util.Date;

/* 
Поговорим о музыке
*/

public class Solution {
    public static int delay = 1000;

    public static void main(String[] args) {
        Thread violin = new Thread(new Violin("Player"));
        violin.start();
    }

    public static void sleepNSeconds(int n) {
        try {
            Thread.sleep(n * delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public interface MusicalInstrument extends Runnable {
        Date startPlaying();

        Date stopPlaying();
    }

    public static class Violin implements MusicalInstrument {

        @Override
        public void run() {
            long startPlaying = startPlaying().getTime();
            sleepNSeconds(1);
            long stopPlaying = stopPlaying().getTime();
            printPlayDuration(startPlaying, stopPlaying);
        }

        private String owner;

        public Violin(String owner) {
            this.owner = owner;
        }

        public void printPlayDuration(long startPlaying, long stopPlaying) {
            long printDuration = stopPlaying- startPlaying;
            System.out.printf("Playing %d ms\n", printDuration);
        }

        public Date startPlaying() {
            System.out.println(this.owner + " is starting to play");
            return new Date();
        }

        public Date stopPlaying() {
            System.out.println(this.owner + " is stopping playing");
            return new Date();
        }
    }
}
