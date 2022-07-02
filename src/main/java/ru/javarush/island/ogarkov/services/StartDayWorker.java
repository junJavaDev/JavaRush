package ru.javarush.island.ogarkov.services;

import ru.javarush.island.ogarkov.entity.Organism;
import ru.javarush.island.ogarkov.location.Cell;
import ru.javarush.island.ogarkov.location.Island;
import ru.javarush.island.ogarkov.location.Territory;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;

public class StartDayWorker implements Callable<Boolean> {
    private static final AtomicLong days = new AtomicLong();
    private final List<Territory> territories;
    private final Queue<Task> tasks;

    public StartDayWorker(Island island) {
        this.territories = new ArrayList<>(island.getTerritories());
        Collections.shuffle(territories);
        tasks = new ConcurrentLinkedQueue<>();
    }

    @Override
    public Boolean call() {
        for (Territory territory : territories) {
            for (Cell cell : territory.getCells()) {
                    processCell(cell);
            }
        }
        days.incrementAndGet();
        return true;
    }

    protected void processCell(Cell cell) {
        cell.getLock().lock();
        Set<Organism> population = cell.getPopulation();
        try {
            for (Organism organism : population) {
                organism.isReproducedTried = false;
                organism.setAge(organism.getAge() + 1);
                Task task = new Task(organism, organismToAction -> organismToAction.die(cell));
                tasks.add(task);
            }
        } finally {
            cell.getLock().unlock();
        }
        tasks.forEach(Task::doAction);
        tasks.clear();
    }
}
