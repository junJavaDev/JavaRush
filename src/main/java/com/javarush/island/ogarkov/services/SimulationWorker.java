package com.javarush.island.ogarkov.services;

import com.javarush.island.ogarkov.entity.Statistics;
import com.javarush.island.ogarkov.location.Island;
import com.javarush.island.ogarkov.location.Territory;
import com.javarush.island.ogarkov.settings.Items;
import com.javarush.island.ogarkov.view.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
        List<OrganismWorker> workers = createWorkers();

        ScheduledExecutorService mainPool = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
        mainPool.scheduleWithFixedDelay(() -> {
            ExecutorService servicePool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
            workers.forEach(servicePool::submit);
            servicePool.submit(new StatisticsWorker(statistics));
//            servicePool.submit(new UpdateViewWorker(controller));
            servicePool.shutdown();
        },1000, 50, TimeUnit.MILLISECONDS );

        ScheduledExecutorService updateablePool = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
        updateablePool.scheduleWithFixedDelay(() -> {
            ExecutorService servicePool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
//            servicePool.submit(new StatisticsWorker(statistics));
            servicePool.submit(new UpdateViewWorker(controller));
            servicePool.shutdown();
        },1000, 10, TimeUnit.MILLISECONDS );



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
