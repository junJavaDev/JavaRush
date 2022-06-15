package ru.javarush.ogarkov.island.interfaces;

import ru.javarush.ogarkov.island.settings.Items;

import java.util.concurrent.ConcurrentHashMap;

public interface StatisticsAction {

    void calculateCreated();
    void calculateTerminated();
    void calculateExisting();

}
