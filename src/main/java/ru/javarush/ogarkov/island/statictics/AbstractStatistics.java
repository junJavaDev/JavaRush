package ru.javarush.ogarkov.island.statictics;

import ru.javarush.ogarkov.island.settings.Items;

import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractStatistics implements Statistics {

    protected Items rootItem;
    protected final ConcurrentHashMap<Items, Long> created = new ConcurrentHashMap<>();
    protected final ConcurrentHashMap<Items, Long> terminated = new ConcurrentHashMap<>();
    protected final ConcurrentHashMap<Items, Long> existing = new ConcurrentHashMap<>();

    @Override

    public ConcurrentHashMap<Items, Long> getCreated() {
        created.put(rootItem, rootItem.getCreatedItemsCount());
         for (Items item : rootItem.getChildren()) {
             created.put(item, item.getCreatedItemsCount());
        }
        return created;
    }

    @Override
    public ConcurrentHashMap<Items, Long> getTerminated() {
        return null;
    }

    @Override
    public ConcurrentHashMap<Items, Long> getExisting() {
        return null;
    }
}
