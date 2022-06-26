package com.javarush.island.ogarkov.services;

import com.javarush.island.ogarkov.entity.Statistics;
import com.javarush.island.ogarkov.interfaces.StatisticsAction;
import com.javarush.island.ogarkov.location.Cell;
import com.javarush.island.ogarkov.location.Island;
import com.javarush.island.ogarkov.location.Territory;
import com.javarush.island.ogarkov.settings.Items;
import com.javarush.island.ogarkov.view.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class StatisticsWorker implements Runnable, StatisticsAction {
    private final Statistics statistics;
    private final List<Territory> territories;
    private final Controller controller;

    public StatisticsWorker(Island island, Controller controller, Statistics statistics) {
        this.statistics = statistics;
        this.controller = controller;
        this.territories = new ArrayList<>(island.getTerritories());
        Collections.shuffle(territories);
    }

    @Override
    public void run() {
        calculateCreated();
        calculateExisting();
        calculateTerminated();
        controller.prepareStatisticForUpdateView();
    }

    @Override
    public void calculateCreated() {
        Map<Items, Integer> created = statistics.getCreated();
        for (Items item : Items.values()) {
            created.put(item, item.getFactory().getCreatedItemsCount());
        }
    }

    @Override
    public void calculateTerminated() {
        for (Items item : Items.values()) {
            int created = statistics.getCreated().get(item);
            int existing = statistics.getExisting().get(item);
            statistics.getTerminated().put(item, created - existing);
        }
    }

    @Override
    public void calculateExisting() {
        Map<Items, Integer> existing = statistics.getExisting();
        existing.keySet().forEach(key -> existing.put(key, 0));
        for (Territory territory : territories) {
            for (Cell cell : territory.getCells()) {
                boolean isLock = cell.getLock().tryLock();
                if (isLock) {
                    try {
                        Items item = cell.getResidentItem();
                        int currentSize = existing.get(item);
                        int populationSize = cell.getPopulation().size();
                        existing.put(item, currentSize + populationSize);
                    } finally {
                        cell.getLock().unlock();
                    }
                }
            }
        }
        calculateParentExisting(Items.PLANT);
        calculateParentExisting(Items.CARNIVORE);
        calculateParentExisting(Items.HERBIVORE);
        calculateParentExisting(Items.ANIMAL);
        calculateParentExisting(Items.LANDFORM);
    }

    private void calculateParentExisting(Items parent) {
        Map<Items, Integer> existing = statistics.getExisting();
        existing.put(parent, parent.getChildren()
                .stream()
                .mapToInt(existing::get)
                .sum());
    }

}
