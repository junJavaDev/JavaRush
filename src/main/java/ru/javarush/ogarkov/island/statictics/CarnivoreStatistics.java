package ru.javarush.ogarkov.island.statictics;

import ru.javarush.ogarkov.island.settings.Items;

public class CarnivoreStatistics extends AbstractStatistics {
    public CarnivoreStatistics() {
        if (rootItem != null){
            rootItem = Items.CARNIVORE;
        }
    }
}
