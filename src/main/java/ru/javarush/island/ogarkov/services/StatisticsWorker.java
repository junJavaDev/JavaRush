package ru.javarush.island.ogarkov.services;

import ru.javarush.island.ogarkov.entity.Statistics;
import ru.javarush.island.ogarkov.interfaces.StatisticsAction;
import ru.javarush.island.ogarkov.location.Cell;
import ru.javarush.island.ogarkov.location.Island;
import ru.javarush.island.ogarkov.location.Territory;
import ru.javarush.island.ogarkov.settings.Items;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class StatisticsWorker implements StatisticsAction {
    private final Statistics statistics;
    private final List<Territory> territories;

    public StatisticsWorker(Island island, Statistics statistics) {
        this.statistics = statistics;
        this.territories = new ArrayList<>(island.getTerritories());
        Collections.shuffle(territories);
    }

    public void calculate() {
        calculateCreated();
        calculateAlive();
        calculateDead();
    }

    @Override
    public void calculateCreated() {
        Map<Items, Integer> created = statistics.getCreated();
        for (Items item : Items.getLowerItems()) {
            created.put(item, item.getFactory().getCreatedItemsCount());
        }
    }

    @Override
    public void calculateDead() {
        for (Items item : Items.getLowerItems()) {
            int created = statistics.getCreated().get(item);
            int existing = statistics.getAlive().get(item);
            statistics.getDead().put(item, created - existing);
        }
    }

    @Override
    public void calculateAlive() {
        Map<Items, Integer> alive = statistics.getAlive();
        alive.keySet().forEach(key -> alive.put(key, 0));
        for (Territory territory : territories) {
            for (Cell cell : territory.getCells()) {
                        Items item = cell.getResidentItem();
                        int currentSize = alive.get(item);
                        int populationSize = cell.getPopulation().size();
                        alive.put(item, currentSize + populationSize);
            }
        }
    }
}
