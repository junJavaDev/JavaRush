import java.util.Scanner;

public class ArrayFillBySnake {

    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);

        System.out.print("Введите ширину массива: ");
        int weight = console.nextInt();
        System.out.print("Введите высоту массива: ");
        int height = console.nextInt();
        int elements = weight * height;

        int[][] array = new int[height][weight];

        int xStart = 0;
        int xEnd = array[0].length - 1;

        int yStart = 0;
        int yEnd = array.length - 1;

        int count = 1;

        while (count <= elements) {

            //проверка на завершение цикла при while (true)
            //if (xStart > xEnd || yStart > yEnd) break;

            //слева направо
            for (int i = xStart; i <= xEnd; i++) { // верхняя строка включает первый и последний элементы
                array[yStart][i] = count;
                count++;
            }

            //сверху вниз
            for (int i = yStart + 1; i <= yEnd; i++) { // правый столбец заполняется со второго элемента, включает последний
                array[i][xEnd] = count;
                count++;
            }

            // проверка для исключения перезаписи значений при нечётном количестве строк/столбцов
            if (yStart == yEnd || xStart == xEnd) break;

            //справа налево
            for (int i = xEnd - 1; i >= xStart; i--) { // нижняя строка заполняется без первого (правого) элемента, включает последний (левый)
                array[yEnd][i] = count;
                count++;
            }

            //снизу вверх
            for (int i = yEnd - 1; i > yStart; i--) { // левый столбец заполняется без первого (нижнего) элемента и не включает последний (верхний)
                array[i][xStart] = count;
                count++;
            }

            yStart++;
            xStart++;
            xEnd--;
            yEnd--;
        }

        print(array);
    }

    private static void print(int[][] array) { // вывод массива в консоль
        int maxDigit = array.length * array[0].length;
        for (int[] ints : array) {
            for (int anInt : ints) {
                System.out.print(anInt + align(anInt, maxDigit));
            }
            System.out.println();
        }
    }

    private static String align(int digit, int maxDigit) { // отступ для выравнивания таблицы
        int countDigit = (digit + "").length();
        int countMax = (maxDigit + "").length();
        int spaces = countMax - countDigit + 1;
        return " ".repeat(spaces);
    }
}
