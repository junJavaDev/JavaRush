package ru.javarush.ogarkov.island.statictics;

import ru.javarush.ogarkov.island.settings.Items;

import java.util.concurrent.ConcurrentHashMap;

public interface Statistics {
    ConcurrentHashMap<Items, Long> getCreated();
    ConcurrentHashMap<Items, Long> getTerminated();
    ConcurrentHashMap<Items, Long> getExisting();
}
