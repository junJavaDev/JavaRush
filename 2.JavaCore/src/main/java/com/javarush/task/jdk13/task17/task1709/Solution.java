package com.javarush.task.jdk13.task17.task1709;

/* 
Предложения
*/

import com.sun.source.tree.SynchronizedTree;

public class Solution {
    public static volatile int proposal = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread accept = new AcceptProposal();
        Thread make = new MakeProposal();
        accept.start();
        make.start();
        Thread.sleep(1500);
        System.out.println("accept - " + accept.getState());
        System.out.println("make - " + make.getState());


    }

    public static class MakeProposal extends Thread {
        @Override
        public void run() {
            int thisProposal = proposal; //thisProposal = 0

            while (proposal < 10) { // 0
                System.out.println("Сделано предложение №" + (thisProposal + 1)); // предложение 1
                proposal = ++thisProposal; //proposal = 1
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }
    }

    public static class AcceptProposal extends Thread {
        @Override
        public void run() {
            int thisProposal = proposal; // thisProposal = 0

            while (thisProposal < 10) {
                if (thisProposal != proposal) {
                    System.out.println("Принято предложение №" + proposal);
                    thisProposal = proposal;
                }

            }
        }
    }
}
