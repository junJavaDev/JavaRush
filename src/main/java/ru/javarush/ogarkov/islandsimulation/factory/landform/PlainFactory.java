package ru.javarush.ogarkov.islandsimulation.factory.landform;

import ru.javarush.ogarkov.islandsimulation.factory.Factory;
import ru.javarush.ogarkov.islandsimulation.item.abstracts.Plant;
import ru.javarush.ogarkov.islandsimulation.item.landform.Plain;
import ru.javarush.ogarkov.islandsimulation.settings.Items;

public class PlainFactory implements Factory {
    @Override
    public Plain createItem(Items item) {
        return new Plain();
    }
}

