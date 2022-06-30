package ru.javarush.island.ogarkov.entity;

import ru.javarush.island.ogarkov.settings.Items;

import java.util.HashMap;
import java.util.Map;

public class Statistics {

    protected final Map<Items, Integer> created;
    protected final Map<Items, Integer> existing;
    protected final Map<Items, Integer> terminated;

    public Statistics() {
        this.created = new HashMap<>();
        this.existing = new HashMap<>();
        this.terminated = new HashMap<>();
        init();
    }

    private void init() {
        for (Items item : Items.values()) {
            created.put(item, 0);
            existing.put(item, 0);
            terminated.put(item, 0);
        }
    }

    public Map<Items, Integer> getCreated() {
        return created;
    }

    public Map<Items, Integer> getTerminated() {
        return terminated;
    }

    public Map<Items, Integer> getExisting() {
        return existing;
    }
}
