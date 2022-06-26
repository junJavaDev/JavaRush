package com.javarush.island.ogarkov.services;

import com.javarush.island.ogarkov.entity.Organism;
import com.javarush.island.ogarkov.exception.IslandException;
import com.javarush.island.ogarkov.location.Cell;
import com.javarush.island.ogarkov.location.Island;
import com.javarush.island.ogarkov.location.Territory;
import com.javarush.island.ogarkov.settings.Setting;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class StartDayWorker implements Runnable {
    private static final AtomicLong days = new AtomicLong();
    private final List<Territory> territories;
    private final ExecutorService workerPool;

    public StartDayWorker(Island island) {
        this.territories = new ArrayList<>(island.getTerritories());
        Collections.shuffle(territories);
        workerPool = Executors.newWorkStealingPool();
    }

    @Override
    public void run() {
        for (Territory territory : territories) {
            for (Cell cell : territory.getCells()) {
                workerPool.execute(() -> processCell(cell));
            }
        }
        days.incrementAndGet();
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
                organism.isReproducedTried = false;
                organism.setAge(organism.getAge() + 1);
                Task task = new Task(organism, action -> organism.die(cell));
                tasks.add(task);
            }
        } finally {
            cell.getLock().unlock();
        }
        tasks.forEach(Task::doAction);
        tasks.clear();
    }
}
