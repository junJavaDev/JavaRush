package ru.javarush.ogarkov.island.factory.plant;

import ru.javarush.ogarkov.island.factory.AbstractFactory;
import ru.javarush.ogarkov.island.entity.plant.Bush;
import ru.javarush.ogarkov.island.settings.Items;

public class BushFactory extends AbstractFactory {
    @Override
    public Bush createItem(Items item) {
        count.incrementAndGet();
        return new Bush();
    }
}
