package ru.javarush.ogarkov.island.factory.carnivore;

import ru.javarush.ogarkov.island.factory.AbstractFactory;
import ru.javarush.ogarkov.island.entity.carnivore.Eagle;
import ru.javarush.ogarkov.island.settings.Items;

public class EagleFactory extends AbstractFactory {
    @Override
    public Eagle createItem(Items item) {
        count.incrementAndGet();
        return new Eagle();
    }
}
