package com.javarush.island.ogarkov.services;

import com.javarush.island.ogarkov.entity.Statistics;
import com.javarush.island.ogarkov.location.Island;
import com.javarush.island.ogarkov.location.Territory;
import com.javarush.island.ogarkov.settings.Items;
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

public class SimulationWorker extends Thread{
    private final Island island;
    private final Controller controller;
    private final Statistics statistics;
    private static final AtomicLong hours = new AtomicLong();


    public SimulationWorker(Island island, Controller controller, Statistics statistics) {
        this.island = island;
        this.controller = controller;
        this.statistics = statistics;
    }

    @Override
    public void run() {
        List<OrganismWorker> workers = createWorkers();

        ScheduledExecutorService mainPool = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
        mainPool.scheduleWithFixedDelay(() -> {
            ExecutorService servicePool = Executors.newWorkStealingPool(Runtime.getRuntime().availableProcessors());
            workers.forEach(servicePool::submit);
            servicePool.submit(new StatisticsWorker(statistics));
            if (hours.get() % 24 == 0) {
                servicePool.submit(new StartDayWorker(island.getTerritories()));
            }
            servicePool.shutdown();
            hours.incrementAndGet();
        },1000, 100, TimeUnit.MILLISECONDS );

        ScheduledExecutorService updateablePool = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
        updateablePool.scheduleWithFixedDelay(() -> {
            ExecutorService servicePool = Executors.newWorkStealingPool(2);
            servicePool.execute(controller::prepareForUpdateView);
            servicePool.execute(() -> Platform.runLater(controller::updateView));
        },1000, 15, TimeUnit.MILLISECONDS );

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
