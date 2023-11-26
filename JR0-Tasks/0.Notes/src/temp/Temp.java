package temp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Temp{
    public static final int array[][] = {
            {1, 1},
            {0, 0}
    };
    public static void invert() {
        int[][] arrayClone = new int[][]{
                array[0].clone(),
                array[1].clone()
        };
        for (int row = 0; row < array.length; row++) {
            for (int col = 0; col < array[row].length; col++) {
                array[row][col] = arrayClone[col][row];
            }
        }
    }
    public static void main(String[] args) {
        invert();
        System.out.println(Arrays.deepToString(array));
    }
}

