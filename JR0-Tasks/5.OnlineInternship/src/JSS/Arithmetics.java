package JSS;

import java.util.Scanner;

/*
Используя IntelliJ IDEA , создайте класс Arithmetics .
Создайте четыре метода для выполнения арифметических операций, с именами : add – сложение, sub –
вычитание, mul – умножение, div – деление . Каждый метод должен принимать два целочисленных
аргумента и выводить на экран результат выполнени я арифметической операции соответствующей
имени метода . Метод деления div , должен выполнять проверку попытки деления на ноль.
Требуется предоставить пользователю возможность вводит ь с клавиатуры значения операндов и знак
арифметической операции , для выполнения вычислений .
*/
public class Arithmetics {
    static int add(int a, int b) {
        return a + b;
    }

    static int sub(int a, int b) {
        return a - b;
    }

    static int mul(int a, int b) {
        return a * b;
    }

    static float div(int a, int b) {
        if (b == 0) {
            System.out.println("На 0 делить нельзя!");
            return a;
        }
        else
            return (float)a / (float)b;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите первое число: ");

        int a = in.nextInt();
        System.out.println("Введите второе число: ");
        int b = in.nextInt();
        System.out.println("Введите знак арифметической операции (+, -, *, /): ");
        String aa = in.next();
        switch (aa) {
            case "/":
                System.out.println(div(a, b));
                break;
            case "*":
                System.out.println(mul(a, b));
                break;
            case "+":
                System.out.println(add(a, b));
                break;
            case "-":
                System.out.println(sub(a, b));
                break;
            default:
                System.out.println("Нет такой операции");
        }
    }
}
