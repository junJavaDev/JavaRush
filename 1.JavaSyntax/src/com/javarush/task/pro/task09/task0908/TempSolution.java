package com.javarush.task.pro.task09.task0908;

public class TempSolution {
    public static void main(String[] args) {
        String binaryNumber = "11010011101000000001111";
        System.out.println("Двоичное число " + binaryNumber + " равно шестнадцатеричному числу " + toHex(binaryNumber));
        String hexNumber = "69d00f";
        System.out.println("Шестнадцатеричное число " + hexNumber + " равно двоичному числу " + toBinary(hexNumber));
    }

    /*
       Общая идея метода toHex():
       1) дописываем исходное винарное число до полного кол-ва разрядов кратному 4 с помощю добавления нулей слева:
       например: дано: 11011 - получаем 0001 1011
       2) Перебераем по очереди в цикле все цифры слева направо отрезая по 4 разряда. Отрезав, переводим бинарное
       значение 4-х разрядного бин числа в десятиное (которое одновременно равно 16-ти ричному).
       3) Полученное значение добавляем в массив int [] hexNew, все элементы которого в совокупности есть 16-тиричное
       представление исходного числа
       4) Конвертируем int [] hexNew - в строку String
        */
    public static String toHex(String binaryNumber) {
        if ((binaryNumber==null) || (binaryNumber.isEmpty())) {  // проверка на null и ""
            return "";
        }
        //==============================
        for (int i = 0; i < binaryNumber.length(); i++) {       // проверка, что binaryNumber = 1 или =0
            int t1 = binaryNumber.charAt(i) - '0';
            if((t1!= 0) && (t1!=1)) {
                return "";
            }
        }
        //==========================             // дополняем разрядность до кратного 4-м: 0000 0000 0000 и т.п.
        int n = (4 - binaryNumber.length() % 4); // определяем кол-во недостающих нулей слева
        for (int i = 0; i < n; i++) {
            binaryNumber = "0" + binaryNumber;  // дописываем слева нули до полного комплекта
        }

        final String HEX = "0123456789abcdef";  // создаем массив-эталон 16-тиричных чисел,
        char[] chars = HEX.toCharArray();      // индекс числа = 16-тиричному числу в 10-тичном виде
        char[] hexNew = new char[binaryNumber.length() / 4]; // создаем массив для записи конечного 16 ричного числа

        int dig4 = 0;                      // номер разряда 16-ричного числа. Нужен для определния соответствующей
        // 4-х разрядной части исходного числа

        int bin2dec = 0; // представление 4-х разрядного фрагмента исходного числа в десятином виде.
        int i;
        for (int j = 3; j >= 0; j--) {   // перебираем байты исх числа (они же соотв. разрядам 16-тиричного)
            int C = 0;                   // счетчик для прохождения в цикле всех 4-х разрядов отдельного байта
            // в цикле проходим исх длинное бинарное число отсекая по 4 цифры - байты с помощью счетчика С.
            // с каждым витком цикл начинается с нового байта, т.к. i увеличивается на 4.
            for (i = dig4; i < binaryNumber.length(); i++) {
                if (C == 4) {
                    break;
                } else {
                    int t1 = binaryNumber.charAt(i) - '0';
                    bin2dec = (int) (t1 * Math.pow(2, 3 - C)) + bin2dec;   //
                    C = C + 1;
                }
            }
            // с помощью массива - эталона 16-тиричн чисел chars[bin2dec] находим символ 16-ричного числа, соответствующего
            // числу текущего байта, выраженного в десятичной форме - bin2dec
            hexNew[3 - j] = chars[bin2dec];    // заполняем массив 16-тиричными цифрами (тип char)
            bin2dec = 0;
            dig4 = dig4 + 4;
            if (i > binaryNumber.length() - 1) {  // проверка: как будет считана последняя цифра из исх бин числа - прерываем цикл
                break;
            }
        }
        String text = String.copyValueOf(hexNew); // конвертируем 16-тиричн число char  в String
        return text;
        //===================
//        String text2="";
//        for (int f = 0; f < text.length()-1; f++) {
//            char c = text.charAt(f);
//            if (c != '0')
//            {
//                text2= text.substring(f);
//            }
//        }           return text2;
    }
    //return ""; // or return "0";
//====================










    public static String toBinary(String hexNumber) {

        if ((hexNumber==null) || (hexNumber.isEmpty())) {  // проверка на null и ""
            return "";
        }
    /*=====================================================
    Проверка: каждая цифра исходного числа должна цифрой от 0 до 9 или буквой от a до f.
    Алгоритм: берем по очередноси каждую букву исходного числа и сравниваем ее с каждым знаком эталонного
    массива знаков. Если хотя бы одна из исходных проверяемых цифр не будет входить в эталонный массив, то
    значение переменной С меняем с 0 на 1. После окончания всей проверки, если С!=0, то возвращаем "".
    */

        String temp = "0123456789abcdef";
        boolean finished = false;
        int C=0;
        for (int i = 0; (i < hexNumber.length() && !finished); i++)
        {
            char t1 = hexNumber.charAt(i);
            for (int j = 0; j < temp.length(); j++)
            {
                if (t1 == temp.charAt(j))
                {
                    break;
                } if (j == temp.length()-1) {
                finished = true;       // если попадается хотя бы одна цифра исходного числа не удовлетворяющая условию, то прерываем оба цикла.
                C=C=1;
                break;
            }
            }

        } if (C!=0) {
            return "";
        }


        final String HEX = "0123456789abcdef";
        int indic = 0;
        String N2="";
        for (int i = 0; i < hexNumber.length(); i++) {
            indic=0;
            for (int j = 0; j < HEX.length(); j++) {
                if (hexNumber.charAt(i) != HEX.charAt(j))
                { } else {
                    indic = indic+1;
                }
            }
            if (indic==0) {
                return "";
            }
        }

        char[] chHEX = HEX.toCharArray();
        String N = "";
        int t = 0;
        for (int i = 0; i < hexNumber.length(); i++) {
            t = HEX.indexOf(hexNumber.charAt(i));

            int Q = 1;
            while (Q != 0) {
                Q = t / 2;
                N = t % 2 + N +"";
                t = Q;
            }
            int n = (4 - N.length());   // определяем кол-во недостающих нулей слева до целого комплекта
            for (int e = 0; e < n; e++) {
                N = "0" + N;  // дописываем слева недостоющие нули
            }
            N2 = N2 + N;
            N="";
        }            return N2;
    }
}
