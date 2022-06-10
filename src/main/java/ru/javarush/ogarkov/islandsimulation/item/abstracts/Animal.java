package ru.javarush.ogarkov.islandsimulation.item.abstracts;

import ru.javarush.ogarkov.islandsimulation.settings.EatingProbability;
import ru.javarush.ogarkov.islandsimulation.settings.Items;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends BasicItem {

    public void eat(BasicItem food) {

        System.out.println("Животное ест растения и/или других животных (если в их локации есть подходящая еда)");
        System.out.println(this.getClass().getSimpleName());
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
        Map<Class<? extends BasicItem>, Integer> eatingPropabilities = EatingProbability.FOOD.get(this.getClass());
        int propability = eatingPropabilities.getOrDefault(food.getClass(), 0);
        return chance < propability;
    }



}
