package ru.javarush.island.ogarkov.entity;

import ru.javarush.island.ogarkov.settings.Items;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Statistics {

    private final Map<Items, Integer> created;
    private final Map<Items, Integer> alive;
    private final Map<Items, Integer> dead;

    public Statistics() {
        this.created = new HashMap<>();
        this.alive = new HashMap<>();
        this.dead = new HashMap<>();
        init();
    }

    public List<Map.Entry<Items, Integer>> getSortedAlive(Items item) {
        return alive.entrySet()
                .stream()
                .filter(entry -> entry.getKey().is(item))
                .sorted((e1, e2) -> -e1.getValue().compareTo(e2.getValue()))
                .toList();
    }

    public Map<Items, Integer> getCreated() {
        return created;
    }

    public Map<Items, Integer> getDead() {
        return dead;
    }

    public Map<Items, Integer> getAlive() {
        return alive;
    }

    private void init() {
        for (Items item : Items.getLowerItems()) {
            created.put(item, 0);
            alive.put(item, 0);
            dead.put(item, 0);
        }
    }
}
