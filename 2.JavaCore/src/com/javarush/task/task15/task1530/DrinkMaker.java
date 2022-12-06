package com.javarush.task.task15.task1530;

public abstract class DrinkMaker {
    abstract void getRightCup(); //Выбрать подходящую чашку;
    abstract void putIngredient(); //положить ингредиенты;
    abstract void pour(); //залить жидкость.
    public void makeDrink() {
        getRightCup();
        putIngredient();
        pour();
    }


}
