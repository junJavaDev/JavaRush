package Temp;

import java.io.*;
import java.util.Arrays;
import java.util.concurrent.BlockingQueue;

public class Temp {
    public static void main(String[] args) {

        System.out.println(Arrays.deepToString(gameField));
        rotateClockwise();
        System.out.println(Arrays.deepToString(gameField));
    }
    private static int[] getNeighbours(int parent, int quantity) {
        int[] neighbours = new int[quantity];
        neighbours[0] = parent;
        int counter = 0;
        for (int i = 1; i < quantity; i++) {
            if (i % 2 != 0) {
                if (parent - 1 - counter >= 0) {
                    neighbours[i] = parent - 1 - counter;
                } else {
                    neighbours[i] = parent + 1 + counter;
                    counter++;
                }
            } else {
                neighbours[i] = parent + 1 + counter;
                counter++;
            }
        }
        return neighbours;
    }

    public static int[][] gameField =
            {{2, 2, 2, 2},
            {4, 4, 4, 4},
            {8, 8, 8, 8},
            {0, 0, 0, 0}};


    private static void rotateClockwise() {
        int[][] temp =
                {{2, 2, 2, 2},
                {4, 4, 4, 4},
                {8, 8, 8, 8},
                {0, 0, 0, 0}};
//        int[][] temp = new int[4][4];
//        for (int i = 0; i < 4; i++) {
//            System.arraycopy(gameField[i], 0, temp[i], 0, 4);
//        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                gameField[i][j] = temp[4-1-j][i];
            }
        }
    }

    public static class Lol implements Runnable {

        @Override
        public void run() {

        }
    }

}
