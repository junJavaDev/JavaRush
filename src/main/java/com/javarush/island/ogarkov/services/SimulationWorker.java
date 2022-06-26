package com.javarush.island.ogarkov.services;

import com.javarush.island.ogarkov.entity.Statistics;
import com.javarush.island.ogarkov.location.Island;
import com.javarush.island.ogarkov.location.Territory;
import com.javarush.island.ogarkov.settings.Items;
import com.javarush.island.ogarkov.settings.Setting;
import com.javarush.island.ogarkov.view.Controller;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class SimulationWorker extends Thread {
    private static final AtomicLong hours = new AtomicLong();
    private final Island island;
    private final Controller controller;
    private final List<OrganismWorker> workers;
    private final StatisticsWorker statisticsWorker;
    private final StartDayWorker startDayWorker;


    public SimulationWorker(Island island, Controller controller, Statistics statistics) {
        this.island = island;
        this.controller = controller;
        workers = createWorkers();
        statisticsWorker = new StatisticsWorker(island, controller, statistics);
        startDayWorker = new StartDayWorker(island);
    }

    // TODO: 26.06.2022 добавить условие завершения симуляции + выход по закрытию окна
    @Override
    public void run() {
        ScheduledExecutorService mainPool = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
        mainPool.scheduleWithFixedDelay(() -> {
            ExecutorService servicePool = Executors.newWorkStealingPool();
            workers.forEach(servicePool::submit);
            servicePool.submit(statisticsWorker);
            if (hours.get() % 24 == 0) {
                servicePool.submit(startDayWorker);
            }
            servicePool.shutdown();
            hours.incrementAndGet();
            servicePool.shutdown();

        }, Setting.INITIAL_DELAY, Setting.MAIN_DELAY, TimeUnit.MILLISECONDS);

        ScheduledExecutorService updateablePool = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
        updateablePool.scheduleWithFixedDelay(() -> {
            ExecutorService servicePool = Executors.newWorkStealingPool();
            servicePool.submit(controller::prepareForUpdateView);
            servicePool.submit(() -> Platform.runLater(controller::updateView));
            servicePool.shutdown();

        }, Setting.INITIAL_DELAY, Setting.UPDATE_DELAY, TimeUnit.MILLISECONDS);
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
