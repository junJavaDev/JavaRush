package Temp;

import java.io.*;
import java.util.Arrays;

public class Temp {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(getNeighbours(5, 9)));
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



}
