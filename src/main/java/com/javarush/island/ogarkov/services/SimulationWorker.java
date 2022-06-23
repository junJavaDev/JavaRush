package com.javarush.island.ogarkov.services;

import com.javarush.island.ogarkov.entity.Statistics;
import com.javarush.island.ogarkov.view.Controller;
import com.javarush.island.ogarkov.location.Island;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SimulationWorker extends Thread{
    private final Island island;
    private final Controller controller;
    private final Statistics statistics;

    public SimulationWorker(Island island, Controller controller, Statistics statistics) {
        this.island = island;
        this.controller = controller;
        this.statistics = statistics;
    }

    @Override
    public void run() {
        ScheduledExecutorService mainPool = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
        mainPool.scheduleAtFixedRate(() -> {
            ExecutorService servicePool = Executors.newFixedThreadPool(3);
            servicePool.submit(new OrganismWorker(island));
            servicePool.submit(new StatisticsWorker(statistics));
            servicePool.submit(new UpdateViewWorker(controller));
            servicePool.shutdown();
        },1000, 100, TimeUnit.MILLISECONDS );

    }



}
