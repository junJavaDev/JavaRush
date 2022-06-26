package com.javarush.island.ogarkov.services;

import com.javarush.island.ogarkov.entity.Organism;
import com.javarush.island.ogarkov.entity.animals.Animal;
import com.javarush.island.ogarkov.exception.IslandException;
import com.javarush.island.ogarkov.location.Cell;
import com.javarush.island.ogarkov.location.Territory;
import com.javarush.island.ogarkov.settings.Items;
import com.javarush.island.ogarkov.settings.Setting;

import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class OrganismWorker implements Runnable {

    private final Items item;
    private final List<Territory> territories;
    private final ExecutorService workerPool;

    public OrganismWorker(Items item, List<Territory> territories) {
        this.item = item;
        this.territories = territories;
        workerPool = Executors.newWorkStealingPool();
    }

    @Override
    public void run() {
        for (Territory territory : territories) {
            for (Cell cell : territory.getCells()) {
                if (item.is(cell.getResidentItem())) {
                    workerPool.execute(() -> processCell(cell));
                }
            }
        }
    }

    public void stopIt() {
        workerPool.shutdown();
        try {
            if (!workerPool.awaitTermination(Setting.INITIAL_DELAY, TimeUnit.MILLISECONDS)) {
                workerPool.shutdownNow();
            }
        } catch (InterruptedException e) {
            throw new IslandException(e);
        }
    }

    protected void processCell(Cell cell) {
        Queue<Task> tasks = new ConcurrentLinkedQueue<>();
        cell.getLock().lock();
        Set<Organism> population = cell.getPopulation();
        try {
            for (Organism organism : population) {
                Task task = new Task(organism, action -> {
                    Items organismItem = organism.getItem();
                    if (item.is(organismItem)) {
                        organism.reproduce(cell);
                        if (item.is(Items.ANIMAL)) {
                            Animal animal = (Animal) organism;
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


