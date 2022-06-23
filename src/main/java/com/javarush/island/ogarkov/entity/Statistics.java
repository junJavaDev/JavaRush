package com.javarush.island.ogarkov.entity;

import com.javarush.island.ogarkov.settings.Items;

import java.util.HashMap;
import java.util.Map;

public class Statistics {

    protected final Map<Items, Long> created;
    protected final Map<Items, Long> terminated;
    protected final Map<Items, Long> existing;

    public Statistics() {
        this.created = new HashMap<>();
        this.terminated = new HashMap<>();
        this.existing = new HashMap<>();
    }

    public Map<Items, Long> getCreated() {
        return created;
    }

    public Map<Items, Long> getTerminated() {
        return terminated;
    }

    public Map<Items, Long> getExisting() {
        return existing;
    }
}
