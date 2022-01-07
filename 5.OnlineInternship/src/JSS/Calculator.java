package JSS;

//Используя IntelliJ IDEA , создайте класс Calculator .
//Создайте метод с именем calculate , который принимает в качестве параметров три целочисленных
//аргумента и выводит на экран среднее арифметическое значений аргументов .
//Самостоятельная деятельность учащегося
public class Calculator {
    static double calculate(int a, int b, int c) {
        double sr = (double)(a + b + c)/3;
        return sr;

    }

    public static void main(String[] args) {
        double lol = calculate(3,2,5);
        System.out.print(lol);
    }
}
