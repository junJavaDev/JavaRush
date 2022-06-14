package ru.javarush.ogarkov.island.factory.landform;

import ru.javarush.ogarkov.island.factory.AbstractFactory;
import ru.javarush.ogarkov.island.entity.landform.Plain;
import ru.javarush.ogarkov.island.settings.Items;

public class PlainFactory extends AbstractFactory {
    @Override
    public Plain createItem(Items item) {
        count.incrementAndGet();
        return new Plain();
    }
}

