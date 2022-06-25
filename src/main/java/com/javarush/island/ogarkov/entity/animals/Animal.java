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
import java.util.Set;
import java.util.concurrent.TimeUnit;

public abstract class Animal extends Organism implements Eating, Movable {

    protected final double foodPerSatiation;
    protected final int maxSpeed;
    protected final Map<Items, Integer> foodRation;
    protected final int maxPerLocation;
    protected double satiety;
    protected double hunger;
    protected int moves;
    protected double maxWeight;

    // TODO: 24.06.2022 убрать из энама эти лимиты
    public Animal() {
        foodPerSatiation = item.getMaxFood();
        satiety = foodPerSatiation * Setting.INITIAL_SATIETY;
        maxSpeed = item.getMaxSpeed();
        foodRation = item.getFoodRation();
        maxPerLocation = item.getMaxCount();
        maxWeight = item.getMaxWeight();
    }

    @Override
    public boolean eat(Cell currentCell) {
        if (isHungry() && atomicEat(currentCell)) {
            return true;
        }
        return atomicLosingWeight(currentCell, Setting.LOSING_WEIGHT_PERCENT);
    }

    @Override
    public boolean move(Cell startCell) {
        Cell destinationCell = getDestinationCell(startCell, maxSpeed);
        return atomicMove(startCell, destinationCell);
    }

    private List<Cell> findFood(Cell currentCell) {
        List<Cell> cellsWithFood = new ArrayList<>();
        for (Cell cell : currentCell.getTerritory().getCells()) {
            if (foodRation.containsKey(cell.getResidentItem())) {
                cellsWithFood.add(cell);
            }
        }
        return cellsWithFood;
    }

    private boolean atomicEat(Cell currentCell) {
        currentCell.getLock().lock();
        Cell cellWithFood = null;
        try {
            List<Cell> cellsWithFood = findFood(currentCell);
            for (Cell cell : cellsWithFood) {
                boolean isLocked = cell.getLock().tryLock(Setting.TRYING_LOCK_MILLIS, TimeUnit.MILLISECONDS);
                Items residentItem = cell.getResidentItem();
                if (isLocked) {
                    if (!cell.getPopulation().isEmpty() && foodRation.containsKey(residentItem)) {
                        cellWithFood = cell;
                        break;
                    } else cell.getLock().unlock();
                }
            }
            if (cellWithFood != null) {
                return eatIt(cellWithFood);
            }
            return false;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            currentCell.getLock().unlock();
            if (cellWithFood != null) {
                cellWithFood.getLock().unlock();
            }
        }
    }

    protected boolean eatIt(Cell cellWithFood) {
        Set<Organism> population = cellWithFood.getPopulation();
        Organism food = population.iterator().next();
        weight = Math.min(maxWeight, weight + food.getWeight());
        population.remove(food);
        if (population.isEmpty()) {
            population.add(cellWithFood.getLandform());
            cellWithFood.setResidentItem(cellWithFood.getLandform().getItem());
        }
        return true;
    }

    protected boolean atomicLosingWeight(Cell currentCell, int percent) {
        currentCell.getLock().lock();
        try {
            if (currentCell.getPopulation().contains(this)) {
                weight -= maxWeight * percent / 100;
                weight = Math.max(0, weight);
                return true;
            }
            return false;
        } finally {
            currentCell.getLock().unlock();
        }
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
            return cellsToMove.stream().min(Cell::compareTo).orElseThrow();
        } else return null;
    }

    private boolean atomicMove(Cell startCell, Cell destinationCell) {
        if (atomicSetTo(destinationCell)) {
            if (atomicPollFrom(startCell)) {
                return true;
            } else atomicPollFrom(destinationCell);
        }
        return false;
    }

    public boolean isHungry() {
        return weight < hunger;
    }

    public double getSatiety() {
        return satiety;
    }

    public void setSatiety(double satiety) {
        this.satiety = satiety;
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

    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }
}
