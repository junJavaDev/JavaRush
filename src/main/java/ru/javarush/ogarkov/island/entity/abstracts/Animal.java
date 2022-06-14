package ru.javarush.ogarkov.island.entity.abstracts;

import ru.javarush.ogarkov.island.entity.carnivore.Bear;
import ru.javarush.ogarkov.island.settings.Items;
import ru.javarush.ogarkov.island.settings.Setting;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends BasicItem {

    protected final double foodPerSatiation = item.getFoodPerSatiation();
    protected double hunger = foodPerSatiation * Setting.HUNGER;
    protected double satiety = foodPerSatiation * Setting.INITIAL_SATIETY;
    protected final int maxSpeed = item.getMaxSpeed();

    public static Animal createAnimal(Items item) {
        return new Bear();
    }




    public void eat(BasicItem food) {
        if (isEdible(food)) {
            if (hunger > satiety) {
                System.out.println(this.getClass().getSimpleName() + " пытается съесть " + food.getClass().getSimpleName());
                if (canEat(food)) {
                    satiety += food.weight;
                    satiety = Math.min(satiety, foodPerSatiation);
                    System.out.println(this.getClass().getSimpleName() + " съел " + food.getClass().getSimpleName() + ", голод утолён на " + satiety/foodPerSatiation*100 + " %");
                    food = food.die();
                }
            } else {
                System.out.println(this.getClass().getSimpleName() + " не голоден");
            }
        } else {
            System.out.println(this.getClass().getSimpleName() + " не ест такую пищу");
        }
    }

    public void move() {
        System.out.println("Животное передвигается (в соседние локации)");
    }

    public void reproduce() {
        System.out.println("Животное размножается (при наличии пары в их локации)");
    }


    private boolean canEat (BasicItem food) {
        ThreadLocalRandom localRandom = ThreadLocalRandom.current();
        int chance = localRandom.nextInt(100);
        Map<Items, Integer> eatingPropabilities = item.getEatingProbability();
        int propability = eatingPropabilities.getOrDefault(food.item, 0);
        return chance < propability;
    }

    private boolean isEdible (BasicItem food) {
        return item.getEatingProbability().containsKey(food.item);
    }





}
