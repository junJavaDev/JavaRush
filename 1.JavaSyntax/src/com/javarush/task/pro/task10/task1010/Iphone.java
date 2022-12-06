package com.javarush.task.pro.task10.task1010;

import java.util.Objects;

/* 
Два айфона
*/

public class Iphone {
    private String model;
    private String color;
    private int price;

    public Iphone(String model, String color, int price) {
        this.model = model;
        this.color = color;
        this.price = price;
    }

    //напишите тут ваш код

    public static void main(String[] args) {
        Iphone iphone1 = new Iphone("X", "Black", 999);
        Iphone iphone2 = new Iphone("X", "Black", 999);
        System.out.println(iphone1.equals(iphone2));


    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Iphone anotherIphone = (Iphone) obj;
        return  this.model != null && this.model.equals(anotherIphone.model) &&
                this.color != null && this.color.equals(anotherIphone.color) &&
                this.price == anotherIphone.price;
    }

    @Override
    public int hashCode() {
        return price + this.model.length() + this.color.length();
    }


}
