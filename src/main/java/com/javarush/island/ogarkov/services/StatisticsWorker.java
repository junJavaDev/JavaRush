package com.javarush.island.ogarkov.services;

import com.javarush.island.ogarkov.entity.Statistics;
import com.javarush.island.ogarkov.settings.Items;
import com.javarush.island.ogarkov.interfaces.StatisticsAction;

import java.util.concurrent.ConcurrentHashMap;

public class StatisticsWorker implements Runnable, StatisticsAction {
    private final Statistics statistics;

    public StatisticsWorker(Statistics statistics) {
        this.statistics = statistics;
    }

    @Override
    public void run() {
        calculateCreated();
    }

    @Override
    public void calculateCreated() {
        ConcurrentHashMap <Items, Long> created = statistics.getCreated();
        for (Items item : Items.values()) {
            created.put(item, item.getFactory().getCreatedItemsCount());
        }
    }

    @Override
    public void calculateTerminated() {

    }

    @Override
    public void calculateExisting() {

    }

}
