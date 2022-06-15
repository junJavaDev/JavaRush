package ru.javarush.ogarkov.island.entity.abstracts;

import ru.javarush.ogarkov.island.entity.carnivore.Bear;
import ru.javarush.ogarkov.island.interfaces.AnimalAction;
import ru.javarush.ogarkov.island.settings.Items;
import ru.javarush.ogarkov.island.settings.Setting;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends BasicItem{

    protected final double foodPerSatiation;
    protected double satiety;
    protected double hunger;
    protected final int maxSpeed;


    public Animal() {
        foodPerSatiation = item.getFoodPerSatiation();
        satiety = foodPerSatiation * Setting.INITIAL_SATIETY;
        maxSpeed = item.getMaxSpeed();
    }

    // TODO: 15.06.2022 Времянка
    public static Animal createAnimal(Items item) {
        return new Bear();
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
