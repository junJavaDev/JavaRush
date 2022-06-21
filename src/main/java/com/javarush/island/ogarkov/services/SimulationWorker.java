package com.javarush.island.ogarkov.services;

import com.javarush.island.ogarkov.Controller;
import com.javarush.island.ogarkov.location.Island;
import com.javarush.island.ogarkov.location.Territory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SimulationWorker extends Thread{
    private final Island island;
    private final Controller controller;
    private final Territory territoryModel;

    public SimulationWorker(Island island, Territory territoryModel, Controller controller) {
        this.island = island;
        this.territoryModel = territoryModel;
        this.controller = controller;
    }

    @Override
    public void run() {
        ScheduledExecutorService mainPool = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
        mainPool.scheduleAtFixedRate(() -> {
            ExecutorService servicePool = Executors.newFixedThreadPool(3);
            servicePool.submit(new OrganismWorker(island));
            servicePool.submit(new OrganismWorker(island));
            servicePool.submit(new UpdateViewWorker(island, territoryModel, controller));
            servicePool.shutdown();
        },1000, 500, TimeUnit.MILLISECONDS );

    }



}
