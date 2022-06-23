package com.javarush.island.ogarkov.location;

import com.javarush.island.ogarkov.entity.Organism;
import com.javarush.island.ogarkov.settings.Items;

import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.javarush.island.ogarkov.settings.Items.*;

// Участок локации, ячейка
public class Cell implements Comparable<Cell> {
    private final Lock lock = new ReentrantLock(true);
    public Territory territory;
    private Set<Organism> population;
    private Items residentItem;

    public Cell(Territory territory) {
        this.territory = territory;
    }

    public Set<Organism> getPopulation() {
        return population;
    }

    public void setPopulation(Set<Organism> population) {
        this.population = population;
    }

    public Territory getTerritory() {
        return territory;
    }

    public Items getResidentItem() {
        return residentItem;
    }

    public void setResidentItem() {
        residentItem = population.iterator().next().getItem();
    }

    @Override
    public int compareTo(Cell secondCell) {
                int result = 0;
                Items firstItem = this.getResidentItem();
                Items secondItem = secondCell.getResidentItem();
                if (firstItem.is(CARNIVORE) && secondItem.isNot(CARNIVORE)) {
                    result = 1;
                } else if (firstItem.isNot(CARNIVORE) && secondItem.is(CARNIVORE)) {
                    result = -1;
                } else if (firstItem.is(HERBIVORE) && secondItem.isNot(HERBIVORE)) {
                    result = 1;
                } else if (firstItem.isNot(HERBIVORE) && secondItem.is(HERBIVORE)) {
                    result = -1;
                } else if (firstItem.is(PLANT) && secondItem.isNot(PLANT)) {
                    result = 1;
                } else if (firstItem.isNot(PLANT) && secondItem.is(PLANT)) {
                    result = -1;
                }

                if (result == 0) {
                    Set<Organism> firstPopulation = this.getPopulation();
                    Set<Organism> secondPopulation = secondCell.getPopulation();

                    double firstTerritoryWeight = firstItem.getWeight() * firstPopulation.size();
                    double secondTerritoryWeight = secondItem.getWeight() * secondPopulation.size();
                    result = Double.compare(firstTerritoryWeight, secondTerritoryWeight);
                }
//            if (result == 0) {
//                var firstCell = first.getKey();
//                var secondCell = second.getKey();
//                return Long.compare(firstCell.getCellId(), secondCell.getCellId());
//            }
                return result;
            }
        }
