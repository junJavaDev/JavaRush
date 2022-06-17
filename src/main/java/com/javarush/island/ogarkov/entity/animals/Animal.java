package com.javarush.island.ogarkov.entity.animals;

import com.javarush.island.ogarkov.entity.Item;
import com.javarush.island.ogarkov.settings.Setting;

public abstract class Animal extends Item {

    protected final double foodPerSatiation;
    protected double satiety;
    protected double hunger;
    protected final int maxSpeed;


    public Animal() {
        foodPerSatiation = item.getFoodPerSatiation();
        satiety = foodPerSatiation * Setting.INITIAL_SATIETY;
        maxSpeed = item.getMaxSpeed();
    }


    public boolean isHungry() {
        return hunger > satiety;
    }

    public double getSatiety() {
        return satiety;
    }

    public double getHunger() {
        return hunger;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public double getFoodPerSatiation() {
        return foodPerSatiation;
    }

    public void setSatiety(double satiety) {
        this.satiety = satiety;
    }
}
