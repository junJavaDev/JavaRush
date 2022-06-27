package com.javarush.island.ogarkov.services;

import com.javarush.island.ogarkov.entity.Statistics;
import com.javarush.island.ogarkov.exception.IslandException;
import com.javarush.island.ogarkov.location.Island;
import com.javarush.island.ogarkov.location.Territory;
import com.javarush.island.ogarkov.settings.Items;
import com.javarush.island.ogarkov.settings.Setting;
import com.javarush.island.ogarkov.view.Controller;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class SimulationWorker extends Thread {
    private static final AtomicLong hours = new AtomicLong();
    private final Island island;
    private final Controller controller;
    private final List<OrganismWorker> workers;
    private final StatisticsWorker statisticsWorker;
    private final StartDayWorker startDayWorker;
    private final ScheduledExecutorService mainPool;
    private final ScheduledExecutorService updateablePool;
    private final ExecutorService mainInnerPool;

    public SimulationWorker(Island island, Controller controller, Statistics statistics) {
        this.island = island;
        this.controller = controller;
        workers = createWorkers();
        statisticsWorker = new StatisticsWorker(island, controller, statistics);
        startDayWorker = new StartDayWorker(island);
        mainPool = Executors.newScheduledThreadPool(Setting.CORE_POOL_SIZE);
        mainInnerPool = Executors.newWorkStealingPool();
        updateablePool = Executors.newScheduledThreadPool(Setting.CORE_POOL_SIZE);
    }

    @Override
    public void run() {
        mainPool.scheduleWithFixedDelay(() -> {
            workers.forEach(mainInnerPool::submit);
            mainInnerPool.submit(statisticsWorker);
            if (hours.get() % 24 == 0) {
                mainInnerPool.submit(startDayWorker);
            }
            hours.incrementAndGet();
        }, Setting.INITIAL_DELAY, Setting.MAIN_DELAY, TimeUnit.MILLISECONDS);

        updateablePool.scheduleWithFixedDelay(() -> {
            Platform.runLater(controller::updateView);
            controller.prepareForUpdateView();
        }, Setting.INITIAL_DELAY, Setting.UPDATE_DELAY, TimeUnit.MILLISECONDS);
    }

    public void stopIt() {
        mainInnerPool.shutdown();
        mainPool.shutdown();
        updateablePool.shutdown();
        try {
            if (!mainInnerPool.awaitTermination(Setting.INITIAL_DELAY, TimeUnit.MILLISECONDS)) {
                mainInnerPool.shutdownNow();
            }
            if (!mainPool.awaitTermination(Setting.INITIAL_DELAY, TimeUnit.MILLISECONDS)) {
                mainPool.shutdownNow();
            }
            if (!updateablePool.awaitTermination(Setting.INITIAL_DELAY, TimeUnit.MILLISECONDS)) {
                updateablePool.shutdownNow();
            }
        } catch (InterruptedException e) {
            throw new IslandException(e);
        }
    }

    private List<OrganismWorker> createWorkers() {
        List<OrganismWorker> workers = new ArrayList<>();
        for (Items organismItem : Items.getOrganismItems()) {
            List<Territory> territories = new ArrayList<>(island.getTerritories());
            Collections.shuffle(territories);
            workers.add(new OrganismWorker(organismItem, territories));
        }
        return workers;
    }


}
