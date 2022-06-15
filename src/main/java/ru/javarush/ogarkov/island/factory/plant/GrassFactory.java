package ru.javarush.ogarkov.island.factory.plant;

import ru.javarush.ogarkov.island.factory.AbstractFactory;
import ru.javarush.ogarkov.island.entity.plant.Grass;
import ru.javarush.ogarkov.island.factory.Factory;
import ru.javarush.ogarkov.island.settings.Items;

public class GrassFactory extends AbstractFactory {
    @Override
    public Grass createItem() {
        Factory parentFactory = Items.GRASS.getParent().getFactory();
        created.incrementAndGet();
        parentFactory.addCreatedItem();
        return new Grass();
    }
}
