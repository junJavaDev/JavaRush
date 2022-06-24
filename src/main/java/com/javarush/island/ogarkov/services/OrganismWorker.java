package com.javarush.island.ogarkov.services;

import com.javarush.island.ogarkov.entity.Organism;
import com.javarush.island.ogarkov.entity.animals.Animal;
import com.javarush.island.ogarkov.location.Cell;
import com.javarush.island.ogarkov.location.Territory;
import com.javarush.island.ogarkov.settings.Items;

import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;

public class OrganismWorker implements Runnable {

    private final Items item;
    private final List<Territory> territories;
    private final Queue<Task> tasks = new ConcurrentLinkedQueue<>();
    private static final AtomicLong clock = new AtomicLong();

    public OrganismWorker(Items item, List<Territory> territories) {
        this.item = item;
        this.territories = territories;
    }

    @Override
    public void run() {
        for (Territory territory : territories) {
            for (Cell cell : territory.getCells()) {
                if (item.is(cell.getResidentItem())) {
                    processCell(cell);
                }
            }
        }
    }

    protected void processCell(Cell cell) {
        cell.getLock().lock();
        Set<Organism> population = cell.getPopulation();
        Items residentItem = cell.getResidentItem();
        try {
            for (Organism organism : population) {
                    Items organismItem = organism.getItem();
                            organism.reproduce(cell);
                Task task = new Task(organism, action -> {

                    if (organismItem.is(Items.ANIMAL)) {
                            Animal animal = (Animal) organism;
                            animal.move(cell);

                    }
                });
                tasks.add(task);
                if (clock.get() % 24 == 0) {
                    organism.isReproducedTried = false;
                }
            }
        } finally {
            cell.getLock().unlock();
        }
        clock.incrementAndGet();
        tasks.forEach(Task::doAction);
        tasks.clear();
    }
}


