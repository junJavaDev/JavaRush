package com.javarush.island.ogarkov.entity;

import com.javarush.island.ogarkov.settings.Items;

import java.util.concurrent.ConcurrentHashMap;

public class Statistics {

    protected final ConcurrentHashMap<Items, Long> created;
    protected final ConcurrentHashMap<Items, Long> terminated;
    protected final ConcurrentHashMap<Items, Long> existing;

    public Statistics() {
        this.created = new ConcurrentHashMap<>();
        this.terminated = new ConcurrentHashMap<>();
        this.existing = new ConcurrentHashMap<>();
    }

    public ConcurrentHashMap<Items, Long> getCreated() {
        return created;
    }

    public ConcurrentHashMap<Items, Long> getTerminated() {
        return terminated;
    }

    public ConcurrentHashMap<Items, Long> getExisting() {
        return existing;
    }
}
