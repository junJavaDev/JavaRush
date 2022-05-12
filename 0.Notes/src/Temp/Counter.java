package Temp;

public class Counter {
    public int countSheeps(Boolean[] arrayOfSheeps) {
        int count = 0;
        for (Boolean isSheep : arrayOfSheeps) {
            if (isSheep != null && isSheep) {
                count++;
            }
        }
        return count;
    }
}
