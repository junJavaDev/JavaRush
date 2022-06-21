package com.javarush.island.ogarkov.entity.animals;

import com.javarush.island.ogarkov.entity.Organism;
import com.javarush.island.ogarkov.interfaces.AnimalAction;
import com.javarush.island.ogarkov.location.Cell;
import com.javarush.island.ogarkov.location.Territory;
import com.javarush.island.ogarkov.settings.Items;
import com.javarush.island.ogarkov.settings.Setting;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends Organism implements AnimalAction {

    protected final double foodPerSatiation;
    protected double satiety;
    protected double hunger;
    protected final int maxSpeed;
    protected final Map<Items, Integer> foodRation;


    public Animal() {
        foodPerSatiation = item.getFoodPerSatiation();
        satiety = foodPerSatiation * Setting.INITIAL_SATIETY;
        maxSpeed = item.getMaxSpeed();
        foodRation = item.getFoodRation();
    }

    @Override
    public void eat(Organism food) {
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
//        System.out.println("in move method");
//        int maxSpeed = item.getMaxSpeed();
//        Territory currentTerritory = cellFrom.getTerritory();
//        Territory[] neighbors = currentTerritory.getNeighbors();
//        int neighboorIndex = Randomizer.getInt(neighbors.length);
//        Territory neighbor = neighbors[neighboorIndex];
//        if (!Collections.disjoint(foodRation.keySet(), neighbor.getResidentsItem())) {
//            Cell cell = neighbor.foundCellByItem(item);
//            if (cell != null) {
//                cell.getPopulation().add(this);
////                cellFrom.getPopulation().remove(this);
//
//            } else {
//                return currentTerritory;
//            }
//            System.out.println("wow");
//
////                Cell outsider = neighbor.foundOutsider();
//
//        }


//        for (Territory neighbor : neighbors) {


//            for (Cell cell : neighbor.getCells().keySet()) {
//                if (this.item.is(cell.getResident().getItem())) {
//                    cell.getPopulation().add(this);
//                    cell.getPopulation().remove(this);
//                    if (sortedCell.getPopulation().isEmpty()) {
//                        Plain plain = new Plain();
//                        cell.getPopulation().add(plain);
//                        cell.setResident(plain);
//                    }
//                    return neighboor;
////                    }
//                }
//            }
//            for (Cell sortedCell : neighboor.getSortedCells()) {
//                if (getItem().getEatingProbability().containsKey(sortedCell.getResident().getItem())) {
//
//                    Cell cellDestination = neighboor.getSortedCells().first();
//                    if (cellDestination.getResident().getItem().isNot(Items.ANIMAL)) {
//                        // TODO: 19.06.2022 Переделать на terminated, добавить обновление
//                        Set<Organism> newPopulation = new RemoveableSet<>();
//                        newPopulation.add(this);
//                        cellDestination.setPopulation(newPopulation);
////                        cellDestination.getPopulation().clear();
//                        sortedCell.setResident(this);
//                        sortedCell.getPopulation().remove(this);
//                        if (sortedCell.getPopulation().isEmpty()) {
//                            Plain plain = new Plain();
//                            sortedCell.getPopulation().add(plain);
//                            sortedCell.setResident(plain);
//                        }
//                        neighboor.getSortedCells().update(cellDestination);
//                        return neighboor;
//                    } else return currentTerritory;
//                }
        return null;
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

    @Override
    public void reproduce() {
        System.out.println("Животное размножается (при наличии пары в их локации)");
    }


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
