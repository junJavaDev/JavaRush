package ru.javarush.ogarkov.island.statictics;


import ru.javarush.ogarkov.island.settings.Items;

public class HerbivoreStatistics extends AbstractStatistics {
    public HerbivoreStatistics() {
        if (rootItem != null) {
            rootItem = Items.HERBIVORE;
        }
    }
}
