package JSS;
/*
Используя IntelliJ IDEA , создайте класс Conversion .
Напишите программу, которая будет выполнять конвертирование валют.
Пользователь вводит:
сумму денег в определенной валюте ,
курс для конвертации в другую валюту.
Организуйте вывод результата операции конвертирования валюты на экран.
 */


import java.util.Scanner;

public class Conversion {
    static double convert(double valuta1, double kurs) {
        return valuta1 * kurs;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите количество валюты:");
        double valuta1 = in.nextDouble();
        System.out.println("Введите курс");
        double kurs = in.nextDouble();
        double valuta2 = convert(valuta1, kurs);


        System.out.println(valuta2);
    }
}
