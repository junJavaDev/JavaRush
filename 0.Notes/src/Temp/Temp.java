package Temp;

import java.io.*;

public class Temp {

/*
Правильный порядок
*/


        public static void main(String[] args) {
            int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
            printArray(array);
            reverseArray(array);
            printArray(array);
        }

        public static void reverseArray(int[] array) {
            int temporary;
            for (int i = 0, j = array.length - 1; i <= array.length / 2; i++, j--)
            {
                temporary = array[i];
                array[i] = array[j];
                array[j] = temporary;
            }
        }

        public static void printArray(int[] array) {
            for (int i : array) {
                System.out.print(i + ", ");
            }
            System.out.println();
        }

}
