package com.javarush.island.ogarkov.entity;

import com.javarush.island.ogarkov.interfaces.Reproducible;
import com.javarush.island.ogarkov.location.Cell;
import com.javarush.island.ogarkov.settings.Items;
import com.javarush.island.ogarkov.util.Randomizer;
import javafx.scene.image.Image;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public abstract class Organism implements Reproducible {
    private final static AtomicLong idCounter = new AtomicLong(System.currentTimeMillis());

    protected String name;
    protected final Items item;
    protected final Image icon;
    protected double weight;
    protected final int maxPerLocation;
    protected Cell cell;
    protected int age;
    public boolean isReproducedTried;
    private long id = idCounter.incrementAndGet();
    protected int lifeLength = 10;

    public Organism() {
        idCounter.incrementAndGet();
        item = Items.valueOf(getClass().getSimpleName().toUpperCase());
        icon = item.getIcon();
        weight = item.getWeight()/2;
        maxPerLocation = item.getMaxPerLocation();
        name = item.getName();
    }

    @Override
    public boolean reproduce(Cell cell) {
        return atomicReproduce(cell, 50);
    }

    protected boolean atomicReproduce(Cell cell, int chance) {
        // TODO: 24.06.2022 вынести в настройки, добавить проверку на голод
        cell.getLock().lock();
        try {
            boolean isBorned = false;
            Set<Organism> population = cell.getPopulation();
            if (!isReproducedTried && population.contains(this) && population.size() < maxPerLocation && population.size() > 1) {
                Organism pair = null;
                for (Organism organism : population) {
                    if (!this.equals(organism)) {
                        pair = organism;
                        break;
                    }
                }
                if (pair != null) {
                    int chanceToReproduce = Randomizer.getInt(100);
                    if (chanceToReproduce < chance) {
                        Organism born = item.getFactory().createItem();
                        population.add(born);
                        isBorned = true;
                    }
                    pair.isReproducedTried = true;
                }
                isReproducedTried = true;
            }
            return isBorned;
        } finally {
            cell.getLock().unlock();
        }
    }

    public Items getItem() {
        return item;
    }

    protected boolean atomicAddTo(Cell cell) {
        cell.getLock().lock();
        try {
            if (item == cell.getResidentItem()) {
                Set<Organism> population = cell.getPopulation();
                int populationSize = population.size();
                return populationSize < maxPerLocation && population.add(this);
            } else return false;
        } finally {
            cell.getLock().unlock();
        }
    }

    protected boolean atomicSetTo(Cell cell) {
        if (atomicAddTo(cell)) {
            return true;
        }
        cell.getLock().lock();
        try {
            Items residentItem = cell.getResidentItem();
            Set<Organism> population = cell.getPopulation();
            if (residentItem.is(Items.PLANT) || residentItem.is(Items.LANDFORM)) {
                population.clear();
                cell.setResidentItem(item);
                return population.add(this);
            } else return false;
        } finally {
            cell.getLock().unlock();
        }
    }

    protected boolean atomicPollFrom(Cell cell) {
        cell.getLock().lock();
        try {
            Set<Organism> population = cell.getPopulation();
            boolean isPolled = population.remove(this);
            if (isPolled && population.isEmpty()) {
                population.add(cell.getLandform());
                cell.setResidentItem(cell.getLandform().getItem());
            }
            return isPolled;
        } finally {
            cell.getLock().unlock();
        }
    }


    public void die(Cell cell) {
        if (age > lifeLength || weight < weight/10) {
            atomicPollFrom(cell);
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Image getIcon() {
        return icon;
    }

    public double getWeight() {
        return weight;
    }

    public Cell getCell() {
        return cell;
    }

    public void setTerritory(Cell cell) {
        this.cell = cell;
    }

    @Override
    public String toString() {
        return "Organizm{" + "name='" + name + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organism organism = (Organism) o;
        return id == organism.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
