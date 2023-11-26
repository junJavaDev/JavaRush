package ru.javarush.island.ogarkov.services;

import javafx.application.Platform;
import ru.javarush.island.ogarkov.entity.Statistics;
import ru.javarush.island.ogarkov.exception.IslandException;
import ru.javarush.island.ogarkov.location.Island;
import ru.javarush.island.ogarkov.location.Territory;
import ru.javarush.island.ogarkov.settings.Items;
import ru.javarush.island.ogarkov.settings.Setting;
import ru.javarush.island.ogarkov.view.javafx.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class SimulationWorker extends Thread {
    private static final AtomicLong halfDay = new AtomicLong();
    private final Island island;
    private final Controller controller;
    private final List<Callable<Boolean>> workers;
    private final List<Callable<Boolean>> workersAtNewDay;
    private final StatisticsWorker statisticsWorker;
    private ScheduledExecutorService mainPool;
    private final ExecutorService mainInnerPool;
    private final int initialDelay;
    private final int mainDelay;

    public SimulationWorker(Island island, Controller controller, Statistics statistics) {
        this.island = island;
        this.controller = controller;
        statisticsWorker = new StatisticsWorker(island, statistics);
        workers = createWorkers();
        StartDayWorker startDayWorker = new StartDayWorker(island);
        workersAtNewDay = new ArrayList<>(workers);
        workersAtNewDay.add(startDayWorker);
        mainPool = Executors.newSingleThreadScheduledExecutor();
        mainInnerPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        initialDelay = Setting.get().getInitialDelay();
        mainDelay = Setting.get().getMainDelay();
    }

    @Override
    public void run() {
        mainPool.scheduleWithFixedDelay(this::lifeCycle, initialDelay, mainDelay, TimeUnit.MILLISECONDS);
    }

    public void stopIt() {
        mainInnerPool.shutdown();
        mainPool.shutdown();
        try {
            if (!mainInnerPool.awaitTermination(initialDelay, TimeUnit.MILLISECONDS)) {
                mainInnerPool.shutdownNow();
            }
            if (!mainPool.awaitTermination(initialDelay, TimeUnit.MILLISECONDS)) {
                mainPool.shutdownNow();
            }
        } catch (InterruptedException e) {
            throw new IslandException(e);
        }
    }

    public void changeSpeed(long period) {
        mainPool.shutdown();
        try {
            if (mainPool.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS)) {
                mainPool = Executors.newSingleThreadScheduledExecutor();
                mainPool.scheduleWithFixedDelay(this::lifeCycle, period, period, TimeUnit.MILLISECONDS);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Callable<Boolean>> createWorkers() {
        List<Callable<Boolean>> workers = new ArrayList<>();
        for (Items organismItem : Items.getAllLowerItems()) {
            List<Territory> territories = new ArrayList<>(island.getTerritories());
            Collections.shuffle(territories);
            workers.add(new OrganismWorker(organismItem, territories));
        }
        return workers;
    }

    private void lifeCycle() {
        try {
            if (halfDay.get() % 2 != 0) {
                mainInnerPool.invokeAll(workers);
            } else {
                mainInnerPool.invokeAll(workersAtNewDay);
            }
        } catch (InterruptedException e) {
            throw new IslandException(e);
        }
        statisticsWorker.calculate();
        controller.prepareForUpdateView();
        Platform.runLater(controller::updateView);
        halfDay.incrementAndGet();
    }
}
