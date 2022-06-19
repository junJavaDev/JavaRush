package com.javarush.island.ogarkov.services;

import com.javarush.island.ogarkov.Controller;
import com.javarush.island.ogarkov.location.Island;
import com.javarush.island.ogarkov.location.Territory;
import javafx.application.Platform;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SimulationWorker extends Thread{
    private final Island island;
    private final Controller controller;

    public SimulationWorker(Island island, Controller controller) {
        this.island = island;
        this.controller = controller;
    }

    @Override
    public void run() {
        ScheduledExecutorService mainPool = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
        mainPool.scheduleAtFixedRate(() -> {
            ExecutorService servicePool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
            servicePool.submit(new OrganizmWorker(island));
//            servicePool.submit(new Worker(controller, island));
            servicePool.shutdown();
        },1000, 400, TimeUnit.MILLISECONDS );

    }



}
