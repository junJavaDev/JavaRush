import java.util.ArrayList;
import java.util.Collections;

public class BinarySearch {
    public static void main(String[] args) {
        ArrayList<Integer> notSortedList = new ArrayList<>() {{
            add(2);
            add(3);
            add(1);
            add(5);
            add(0);
            add(4);
        }};
        ArrayList<Integer> sortedList = new ArrayList<>(notSortedList);
        Collections.sort(sortedList);
        System.out.println(notSortedList);
        System.out.println(sortedList);
        System.out.println(binarySearch(sortedList, 3));
    }

    public static int binarySearch(ArrayList<Integer> list, Integer value) {
        int index = -1;
        int low = 0;
        int high = list.size() - 1;
        int middle = (low + high) / 2;
        while (low <= high) {
            int middleValue = list.get(middle);
            if (value < middleValue) {
                high = middle - 1;
            } else if (value.equals(middleValue)) {
                index = middle;
                break;
            } else {
                low = middle + 1;
            }
            middle = (low + high) / 2;
        }
        return index;
    }
}

