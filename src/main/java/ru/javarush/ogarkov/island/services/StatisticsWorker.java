package ru.javarush.ogarkov.island.services;

import ru.javarush.ogarkov.island.entity.Statistics;
import ru.javarush.ogarkov.island.interfaces.StatisticsAction;
import ru.javarush.ogarkov.island.settings.Items;

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
