package ru.javarush.island.ogarkov.entity.animals;

import ru.javarush.island.ogarkov.entity.Organism;
import ru.javarush.island.ogarkov.exception.IslandException;
import ru.javarush.island.ogarkov.interfaces.Eating;
import ru.javarush.island.ogarkov.interfaces.Movable;
import ru.javarush.island.ogarkov.location.Cell;
import ru.javarush.island.ogarkov.location.Territory;
import ru.javarush.island.ogarkov.settings.Items;
import ru.javarush.island.ogarkov.settings.Setting;
import ru.javarush.island.ogarkov.util.Randomizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static ru.javarush.island.ogarkov.settings.Setting.TRYING_LOCK_MILLIS;

public abstract class Animal extends Organism implements Eating, Movable {

    protected final Map<Items, Integer> foodRation;
    protected double satiety;
    protected double hunger;
    protected int moves;

    public Animal() {
        satiety = item.getMaxFood() * Setting.INIT_SATIETY;
        foodRation = item.getFoodRation();
        weight = item.getMaxWeight() * Setting.INIT_WEIGHT;
    }

    @Override
    public void eat(Cell currentCell) {
        if (isHungry() && atomicEat(currentCell)) {
            return;
        }
        atomicLosingWeight(currentCell);
    }

    @Override
    public void move(Cell startCell) {
        if (moves > 0) {
            Cell destinationCell = getDestinationCell(startCell);
            if (atomicMove(startCell, destinationCell) && moves > 0) {
                move(destinationCell);
            }
        }
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }
    protected boolean eatIt(Cell cellWithFood) {
        Set<Organism> foodPopulation = cellWithFood.getPopulation();
        Organism food = foodPopulation.iterator().next();
        int chanceToEat = item.getFoodRation().get(food.getItem());
        if (chanceToEat > Randomizer.getInt(100)) {
            weight = Math.min(
                    item.getMaxWeight(),
                    weight + Math.min(item.getMaxFood(), food.getWeight()));
            foodPopulation.remove(food);
            if (foodPopulation.isEmpty()) {
                foodPopulation.add(cellWithFood.getLandform());
                cellWithFood.setResidentItem(cellWithFood.getLandform().getItem());
            }
            return true;
        } else return false;
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
                boolean isLocked = cell.getLock().tryLock(TRYING_LOCK_MILLIS, TimeUnit.MILLISECONDS);
                if (isLocked) {
                    Items residentItem = cell.getResidentItem();
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
            throw new IslandException(e);
        } finally {
            currentCell.getLock().unlock();
            if (cellWithFood != null) {
                cellWithFood.getLock().unlock();
            }
        }
    }


    private void atomicLosingWeight(Cell currentCell) {
        currentCell.getLock().lock();
        try {
            if (currentCell.getPopulation().contains(this)) {
                double losingWeight = item.getMaxWeight() * Setting.LOSING_WEIGHT_PERCENT;
                weight = Math.max(0, weight - losingWeight);
                satiety = Math.max(0, satiety - losingWeight);
            }
        } finally {
            currentCell.getLock().unlock();
        }
    }

    private Cell getDestinationCell(Cell startCell) {
        List<Territory> adjacentTerritory = startCell.getTerritory().getAdjacentTerritory();
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
                moves = Math.min(0, moves - Randomizer.getIntOriginOne(item.getMaxSpeed()));
                return true;
            } else atomicPollFrom(destinationCell);
        }
        return false;
    }

    private boolean isHungry() {
        return satiety < hunger;
    }
}
