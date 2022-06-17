package com.javarush.island.ogarkov.services;

import com.javarush.island.ogarkov.entity.Item;
import com.javarush.island.ogarkov.location.Cell;
import com.javarush.island.ogarkov.util.Randomizer;
import com.javarush.island.ogarkov.entity.animals.Animal;
import com.javarush.island.ogarkov.interfaces.AnimalAction;
import com.javarush.island.ogarkov.location.Territory;
import com.javarush.island.ogarkov.settings.Items;
import com.javarush.island.ogarkov.settings.Setting;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class ItemWorker implements AnimalAction {

    private final Animal animal;
    private final Items item;

    public ItemWorker(Animal animal) {
        this.animal = animal;
        item = animal.getItem();
    }

    @Override
    public void eat(Item food) {
        if (isEdible(food)) {
            if (animal.isHungry()) {
                System.out.println(this.getClass().getSimpleName() + " пытается съесть " + food.getClass().getSimpleName());
                if (canEat(food)) {
                    animal.setSatiety(Math.min(animal.getSatiety() + food.getWeight(), animal.getFoodPerSatiation()));
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
    public void move() {
        Cell currentCell = animal.getTerritory();
        int maxSpeed = animal.getMaxSpeed();
        List<Territory> availableTerritories = getAvailableLocations(currentCell, maxSpeed);
        // TODO: 16.06.2022 Добавить логику выбора территории, по принципу самой слабой единицы,
        //  добавить логику перемещения, если голоден и нет еды
        //  добавить логику выбора локации по принципу где есть еда
        Territory territoryToMove = availableTerritories.get(Randomizer.getInt(availableTerritories.size()));
        if (territoryToMove == currentCell.getLocation()) return;
        // TODO: 15.06.2022 закинуть логику передвижения в пул обновление отображения в контроллёр
        //  добавить логику выбрать другую локацию, если на той территории нет растений или почвы,
        //  добавить логику перемещения к таким же итемам в их популяцию
        //  установить максимум на клетку равный максимум на локацию / 16
        //  уменьшить цветки

        territoryToMove.getCells()[0][0].setPopulation(new Item[]{animal});
        animal.setTerritory(territoryToMove.getCells()[0][0]);
        territoryToMove.getLeader().setIslandCellColor();

        Item plain = Items.PLAIN.getFactory().createItem();
        currentCell.setPopulation(new Item[]{plain});
        plain.setTerritory(currentCell);
        currentCell.getLocation().getLeader().setIslandCellColor();

        territoryToMove.addMouseClickedAction();
        currentCell.getLocation().addMouseClickedAction();
        System.out.println("Животное передвигается (в соседние локации)");
    }

    @Override
    public void reproduce() {
        System.out.println("Животное размножается (при наличии пары в их локации)");
    }


    private boolean canEat(Item food) {
        ThreadLocalRandom localRandom = ThreadLocalRandom.current();
        int chance = localRandom.nextInt(100);
        Map<Items, Integer> eatingPropabilities = item.getEatingProbability();
        int propability = eatingPropabilities.getOrDefault(food.getItem(), 0);
        return chance < propability;
    }

    private boolean isEdible(Item food) {
        return item.getEatingProbability().containsKey(food.getItem());
    }

    public List<Territory> getAvailableLocations(Cell currentCell, int maxSpeed) {
        Territory currentTerritory = currentCell.getLocation();
        List<Territory> availableTerritories = new ArrayList<>();
        Territory[][] islandTerritories = currentTerritory.getIsland().getLocations();
        int currentXPosition = currentTerritory.getXPosition();
        int currentYPosition = currentTerritory.getYPosition();
        int minXPosition = Math.max(currentXPosition - maxSpeed, 0);
        int maxXPosition = Math.min(currentXPosition + maxSpeed, Setting.ISLAND_WIDTH - 1);
        int minYPosition = Math.max(currentYPosition - maxSpeed, 0);
        int maxYPosition = Math.min(currentYPosition + maxSpeed, Setting.ISLAND_HEIGHT - 1);
            for (int x = minXPosition; x <= maxXPosition; x++) {
                for (int y = minYPosition; y <= maxYPosition; y++) {
                    availableTerritories.add(islandTerritories[x][y]);
                }
            }
        return availableTerritories;
    }

}
