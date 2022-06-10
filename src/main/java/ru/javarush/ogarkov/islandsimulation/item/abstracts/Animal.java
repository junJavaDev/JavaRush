package ru.javarush.ogarkov.islandsimulation.item.abstracts;

import ru.javarush.ogarkov.islandsimulation.settings.Items;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends BasicItem {

    protected double satiety;
    protected final double foodPerSatiation = item.getFoodPerSatiation();

    public void eat(BasicItem food) {
        satiety = 1;
        System.out.println(this.getClass().getSimpleName() + " пытается съесть " + food.getClass().getSimpleName());

        if (satiety < foodPerSatiation && canEat(food)) {
            satiety += food.weight;
            satiety = Math.min(satiety, foodPerSatiation);
            System.out.println(this.getClass().getSimpleName() + " съел " + food.getClass().getSimpleName() + " и теперь весит " + weight + " кг");
        }
//        System.out.println("Животное ест растения и/или других животных (если в их локации есть подходящая еда)");
    }

    public void move() {
        System.out.println("Животное передвигается (в соседние локации)");
    }

    public void reproduce() {
        System.out.println("Животное размножается (при наличии пары в их локации)");
    }

    public void die() {
        System.out.println("Животное умирает (от голода или съедено)");
    }

    private boolean canEat (BasicItem food) {
        ThreadLocalRandom localRandom = ThreadLocalRandom.current();
        int chance = localRandom.nextInt(100);
        Map<Items, Integer> eatingPropabilities = item.getEatingProbability();
        System.out.println(item.name());
        System.out.println(eatingPropabilities);
        int propability = eatingPropabilities.getOrDefault(food.item, 0);
        return chance < propability;
    }



}
