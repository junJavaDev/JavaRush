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

public class OrganismWorker implements Runnable {

    private final Items item;
    private final List<Territory> territories;
    private final Queue<Task> tasks;


    public OrganismWorker(Items item, List<Territory> territories) {
        this.item = item;
        this.territories = territories;
        tasks = new ConcurrentLinkedQueue<>();
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
        try {
            for (Organism organism : population) {
                Task task = new Task(organism, organismToAction -> {
                    Items organismItem = organismToAction.getItem();
                    if (item.is(organismItem)) {
                        organismToAction.reproduce(cell);
                        if (item.is(Items.ANIMAL)) {
                            Animal animal = (Animal) organismToAction;
                            animal.eat(cell);
                            animal.move(cell);
                        }
                    }
                });
                tasks.add(task);
            }
        } finally {
            cell.getLock().unlock();
        }
        tasks.forEach(Task::doAction);
        tasks.clear();
    }
}


