package ru.javarush.ogarkov.island.repository.itemfactory.plant;

import ru.javarush.ogarkov.island.repository.itemfactory.AbstractFactory;
import ru.javarush.ogarkov.island.repository.itemfactory.Factory;
import ru.javarush.ogarkov.island.entity.plant.Sprout;
import ru.javarush.ogarkov.island.settings.Items;

public class SproutFactory extends AbstractFactory {
    @Override
    public Sprout createItem() {
        Factory parentFactory = Items.SPROUT.getParent().getFactory();
        created.incrementAndGet();
        parentFactory.addCreatedItem();
        return new Sprout();
    }
}
