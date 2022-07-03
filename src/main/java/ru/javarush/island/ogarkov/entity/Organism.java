package ru.javarush.island.ogarkov.entity;

import ru.javarush.island.ogarkov.interfaces.Reproducible;
import ru.javarush.island.ogarkov.location.Cell;
import ru.javarush.island.ogarkov.settings.Items;
import ru.javarush.island.ogarkov.util.Randomizer;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public abstract class Organism implements Reproducible {
    private final static AtomicLong idCounter = new AtomicLong(System.currentTimeMillis());
    private final long id = idCounter.incrementAndGet();
    protected final Items item;
    public boolean isReproduced;
    protected String name;
    protected double weight;
    protected int age;
    protected int lifeLength;
    protected int chanceToReproduce;

    public Organism() {
        idCounter.incrementAndGet();
        item = Items.valueOf(getClass().getSimpleName().toUpperCase());
        weight = item.getMaxWeight();
        name = item.getName();
    }
    @Override
    public void reproduce(Cell cell) {
        atomicReproduce(cell, chanceToReproduce);
    }

    public Items getItem() {
        return item;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void die(Cell cell) {
        if (age > lifeLength || weight == 0) {
            atomicPollFrom(cell);
        }
    }

    protected void atomicReproduce(Cell cell, int chance) {
        cell.getLock().lock();
        try {
            Set<Organism> population = cell.getPopulation();
            if (!isReproduced && population.contains(this)
                    && population.size() < item.getMaxCount()
                    && population.size() > 1) {
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
                        pair.isReproduced = true;
                        isReproduced = true;
                    }
                }
            }
        } finally {
            cell.getLock().unlock();
        }
    }


    protected boolean atomicAddTo(Cell cell) {
        cell.getLock().lock();
        try {
            if (item == cell.getResidentItem()) {
                Set<Organism> population = cell.getPopulation();
                int populationSize = population.size();
                return populationSize <
                        item.getMaxCount()
                        && population.add(this);
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
