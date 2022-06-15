package ru.javarush.ogarkov.island.services;

import javafx.application.Platform;
import ru.javarush.ogarkov.island.entity.abstracts.Animal;
import ru.javarush.ogarkov.island.entity.abstracts.BasicItem;
import ru.javarush.ogarkov.island.entity.landform.Plain;
import ru.javarush.ogarkov.island.interfaces.AnimalAction;
import ru.javarush.ogarkov.island.location.Location;
import ru.javarush.ogarkov.island.location.Territory;
import ru.javarush.ogarkov.island.settings.Items;
import ru.javarush.ogarkov.island.settings.Setting;
import ru.javarush.ogarkov.island.util.Randomizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class AnimalWorker implements AnimalAction {

    private final Animal animal;
    private final Items item;

    public AnimalWorker(Animal animal) {
        this.animal = animal;
        item = animal.getItem();
    }

    @Override
    public void eat(BasicItem food) {
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
        Territory currentTerritory = animal.getTerritory();
        int maxSpeed = animal.getMaxSpeed();
        List<Location> availableLocations = getAvailableLocations(currentTerritory, maxSpeed);
        Location locationToMove = availableLocations.get(Randomizer.getInt(availableLocations.size()));
        // TODO: 15.06.2022 закинуть логику передвижения в пул

        locationToMove.getTerritories()[0][0].setPopulation(new BasicItem[]{animal});
        animal.setTerritory(locationToMove.getTerritories()[0][0]);
        locationToMove.getLeader().setIslandCellColor();

        BasicItem plain = Items.PLAIN.getFactory().createItem();
        currentTerritory.setPopulation(new BasicItem[]{plain});
        plain.setTerritory(currentTerritory);
        currentTerritory.getLocation().getLeader().setIslandCellColor();

        locationToMove.addMouseClickedAction();
        currentTerritory.getLocation().addMouseClickedAction();
        System.out.println("Животное передвигается (в соседние локации)");
    }

    @Override
    public void reproduce() {
        System.out.println("Животное размножается (при наличии пары в их локации)");
    }


    private boolean canEat(BasicItem food) {
        ThreadLocalRandom localRandom = ThreadLocalRandom.current();
        int chance = localRandom.nextInt(100);
        Map<Items, Integer> eatingPropabilities = item.getEatingProbability();
        int propability = eatingPropabilities.getOrDefault(food.getItem(), 0);
        return chance < propability;
    }

    private boolean isEdible(BasicItem food) {
        return item.getEatingProbability().containsKey(food.getItem());
    }

    public List<Location> getAvailableLocations(Territory currentTerritory, int maxSpeed) {
        Location currentLocation = currentTerritory.getLocation();
        List<Location> availableLocations = new ArrayList<>();
        Location[][] islandLocations = currentLocation.getIsland().getLocations();
        int currentXPosition = currentLocation.getXPosition();
        int currentYPosition = currentLocation.getYPosition();
        int minXPosition = Math.max(currentXPosition - maxSpeed, 0);
        int maxXPosition = Math.min(currentXPosition + maxSpeed, Setting.ISLAND_WIDTH - 1);
        int minYPosition = Math.max(currentYPosition - maxSpeed, 0);
        int maxYPosition = Math.min(currentYPosition + maxSpeed, Setting.ISLAND_HEIGHT - 1);
        for (int speed = 0; speed < maxSpeed; speed++) {
            for (int x = minXPosition; x <= maxXPosition; x++) {
                for (int y = minYPosition; y <= maxYPosition; y++) {
                    availableLocations.add(islandLocations[x][y]);
                }
            }
        }
        return availableLocations;
    }

}
