package ru.javarush.ogarkov.island.repository.itemfactory.plant;

import ru.javarush.ogarkov.island.repository.itemfactory.AbstractFactory;
import ru.javarush.ogarkov.island.repository.itemfactory.Factory;
import ru.javarush.ogarkov.island.entity.plant.Flower;
import ru.javarush.ogarkov.island.settings.Items;

public class FlowerFactory extends AbstractFactory {
    @Override
    public Flower createItem() {
        Factory parentFactory = Items.FLOWER.getParent().getFactory();
        created.incrementAndGet();
        parentFactory.addCreatedItem();
        return new Flower();
    }
}
