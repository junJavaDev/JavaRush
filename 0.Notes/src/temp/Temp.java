package temp;

import java.util.Arrays;

public class Temp extends A{
    private static int index = 4;
    private static Integer[] array = {0, 1, 2, 3, 4, 5};

    public static void main(String[] args) throws InterruptedException {
        runTasks();
    }

    private static void runTasks() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            new Thread(task).start();
            index++;
            Thread.sleep(10);
            if (index == 6) {
                index = 0;
            }
        }
    }

    private static void printIndexIfExist(Integer index, Integer[] array) {
        Arrays.stream(array).forEach(value -> {
            if (index.equals(value)) {
                System.out.println(Thread.currentThread().getName() + " = " + index);
            }
        });
    }

    private static Runnable task = () -> printIndexIfExist(index, array);




}
