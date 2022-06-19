package com.javarush.island.ogarkov.entity.animals;

import com.javarush.island.ogarkov.entity.Organizm;
import com.javarush.island.ogarkov.entity.landform.Plain;
import com.javarush.island.ogarkov.interfaces.AnimalAction;
import com.javarush.island.ogarkov.location.Cell;
import com.javarush.island.ogarkov.location.Territory;
import com.javarush.island.ogarkov.settings.Items;
import com.javarush.island.ogarkov.settings.Setting;
import com.javarush.island.ogarkov.util.Randomizer;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends Organizm implements AnimalAction {

    protected final double foodPerSatiation;
    protected double satiety;
    protected double hunger;
    protected final int maxSpeed;


    public Animal() {
        foodPerSatiation = item.getFoodPerSatiation();
        satiety = foodPerSatiation * Setting.INITIAL_SATIETY;
        maxSpeed = item.getMaxSpeed();
    }

    @Override
    public void eat(Organizm food) {
        if (isEdible(food)) {
            if (isHungry()) {
                System.out.println(this.getClass().getSimpleName() + " пытается съесть " + food.getClass().getSimpleName());
                if (canEat(food)) {
                    setSatiety(Math.min(getSatiety() + food.getWeight(), getFoodPerSatiation()));
                    System.out.println(this.getClass().getSimpleName() + " съел " + food.getClass().getSimpleName());
                    food.terminated();
                }
            } else {
                System.out.println(this.getClass().getSimpleName() + " не голоден");
            }
        } else {
            System.out.println(this.getClass().getSimpleName() + " не ест такую пищу");
        }
    }

    @Override
    public Territory move(Cell cellFrom) {
        int maxSpeed = item.getMaxSpeed();
        Territory currentTerritory = cellFrom.getTerritory();
        Set<Territory> neighboors = currentTerritory.getNeighbors();
//        System.out.println("CURRENT = " + currentTerritory);
        Territory[] neighboorsArray = neighboors.toArray(Territory[]::new);
        int neighboorIndex = Randomizer.getInt(neighboorsArray.length);
        Territory neighboor = neighboorsArray[neighboorIndex];

//        neighboors.forEach(System.out::println);
//        for (Territory neighboor : neighboors) {

//            if (getItem().getEatingProbability().containsKey(sortedCell.getResident().getItem())) {

            for (Cell sortedCell : neighboor.getSortedCells()) {
//                System.out.println("this item = " + this.item);
//                System.out.println(sortedCell.getResident().getItem());
                if (this.item.is(sortedCell.getResident().getItem())) {
                    sortedCell.getPopulation().add(this);
                    neighboor.getSortedCells().update(sortedCell);
                    return neighboor;
//                    }
                }
            }
            for (Cell sortedCell : neighboor.getSortedCells()) {
                if (getItem().getEatingProbability().containsKey(sortedCell.getResident().getItem())) {

                    Cell cellDestination = neighboor.getSortedCells().first();
                    if (cellDestination.getResident().getItem().isNot(Items.ANIMAL)) {
                        // TODO: 19.06.2022 Переделать на terminated
                        cellDestination.getPopulation().clear();
                        cellDestination.getPopulation().add(this);
                        neighboor.getSortedCells().update(cellDestination);
                        return neighboor;
                    } else return currentTerritory;
                }
            }
//        }

        // TODO: 16.06.2022 Добавить логику выбора ячейки, по принципу самой слабой единицы,
        //  добавить логику перемещения, если голоден и нет еды
        //  добавить логику выбора локации по принципу где есть еда

        // TODO: 15.06.2022 закинуть логику передвижения в пул обновление отображения в контроллёр
        //  добавить логику выбрать другую локацию, если на той территории нет растений или почвы,
        //  добавить логику перемещения к таким же итемам в их популяцию
        //  установить максимум на клетку равный максимум на локацию / 16
        //  уменьшить цветки

        return cellFrom.getTerritory();
    }

    @Override
    public void reproduce() {
        System.out.println("Животное размножается (при наличии пары в их локации)");
    }


    private boolean canEat(Organizm food) {
        ThreadLocalRandom localRandom = ThreadLocalRandom.current();
        int chance = localRandom.nextInt(100);
        Map<Items, Integer> eatingPropabilities = item.getEatingProbability();
        int propability = eatingPropabilities.getOrDefault(food.getItem(), 0);
        return chance < propability;
    }

    private boolean isEdible(Organizm food) {
        return item.getEatingProbability().containsKey(food.getItem());
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
