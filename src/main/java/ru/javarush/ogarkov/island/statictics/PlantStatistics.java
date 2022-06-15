package ru.javarush.ogarkov.island.statictics;

import ru.javarush.ogarkov.island.settings.Items;

public class PlantStatistics extends AbstractStatistics{
    public PlantStatistics() {
        if (rootItem != null) {
            rootItem = Items.PLANT;
        }
    }
}
