package ru.javarush.ogarkov.island.repository.itemfactory.plant;

import ru.javarush.ogarkov.island.repository.itemfactory.AbstractFactory;
import ru.javarush.ogarkov.island.repository.itemfactory.Factory;
import ru.javarush.ogarkov.island.entity.plant.Grass;
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
