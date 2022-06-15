package ru.javarush.ogarkov.island.statictics;

import ru.javarush.ogarkov.island.settings.Items;

public class LandformStatistics extends AbstractStatistics {
    public LandformStatistics() {
        if (rootItem != null) {
            rootItem = Items.LANDFORM;
        }
    }
}
