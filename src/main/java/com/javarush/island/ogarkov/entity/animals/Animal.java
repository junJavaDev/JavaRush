package com.javarush.island.ogarkov.entity.animals;

import com.javarush.island.ogarkov.entity.Organism;
import com.javarush.island.ogarkov.interfaces.Eating;
import com.javarush.island.ogarkov.interfaces.Movable;
import com.javarush.island.ogarkov.location.Cell;
import com.javarush.island.ogarkov.location.Territory;
import com.javarush.island.ogarkov.settings.Items;
import com.javarush.island.ogarkov.settings.Setting;
import com.javarush.island.ogarkov.util.Randomizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends Organism implements Eating, Movable {

    protected final double foodPerSatiation;
    protected double satiety;
    protected double hunger;
    protected final int maxSpeed;
    protected final Map<Items, Integer> foodRation;
    protected double maxWeight;
    protected final int maxPerLocation;

    // TODO: 24.06.2022 убрать из энама эти лимиты
    public Animal() {
        foodPerSatiation = item.getFoodPerSatiation();
        satiety = foodPerSatiation * Setting.INITIAL_SATIETY;
        maxSpeed = item.getMaxSpeed();
        foodRation = item.getFoodRation();
        maxPerLocation = item.getMaxPerLocation();
        maxWeight = item.getWeight();
    }

    @Override
    public boolean eat(Cell currentCell) {
//        if (isEdible(food)) {
//            if (isHungry()) {
//                System.out.println(this.getClass().getSimpleName() + " пытается съесть " + food.getClass().getSimpleName());
//                if (canEat(food)) {
//                    setSatiety(Math.min(getSatiety() + food.getWeight(), getFoodPerSatiation()));
//                    System.out.println(this.getClass().getSimpleName() + " съел " + food.getClass().getSimpleName());
//                    food.terminated();
//                }
//            } else {
//                System.out.println(this.getClass().getSimpleName() + " не голоден");
//            }
//        } else {
//            System.out.println(this.getClass().getSimpleName() + " не ест такую пищу");
//        }
        return true;
    }

    @Override
    public boolean move(Cell startCell) {
        Cell destinationCell = getDestinationCell(startCell, maxSpeed);
        return atomicMove(startCell, destinationCell);
    }



    private boolean atomicMove(Cell startCell, Cell destinationCell) {
        if (atomicSetTo(destinationCell)) {
            if (atomicPollFrom(startCell)) {
                return true;
            } else atomicPollFrom(destinationCell);
        }
        return false;
    }

    private Cell getDestinationCell(Cell startCell, int maxSpeed) {
        List<Territory> adjacentTerritory = startCell.getTerritory().getAdjacentTerritory();
        // TODO: 24.06.2022 рандомайзер можно улучшить
        Territory adjacent = adjacentTerritory.get(Randomizer.getInt(adjacentTerritory.size()));

        List<Cell> cellsToMove = new ArrayList<>();
        for (Cell cell : adjacent.getCells()) {
            Items residentItem = cell.getResidentItem();
            if (item.is(residentItem)) {
                return cell;
            } else if (residentItem.is(Items.PLANT) || residentItem.is(Items.LANDFORM)) {
                cellsToMove.add(cell);
            }
        }
        if (!cellsToMove.isEmpty()) {
            return cellsToMove.stream()
                    .min(Cell::compareTo)
                    .orElseThrow();
        }
        else return null;
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

//        return cellFrom.getTerritory();
//    }

//    @Override
//    public boolean reproduce() {
//        System.out.println("Животное размножается (при наличии пары в их локации)");
//        return false;
//    }


    private boolean canEat(Organism food) {
        ThreadLocalRandom localRandom = ThreadLocalRandom.current();
        int chance = localRandom.nextInt(100);
        Map<Items, Integer> eatingPropabilities = item.getFoodRation();
        int propability = eatingPropabilities.getOrDefault(food.getItem(), 0);
        return chance < propability;
    }

    private boolean isEdible(Organism food) {
        return item.getFoodRation().containsKey(food.getItem());
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
