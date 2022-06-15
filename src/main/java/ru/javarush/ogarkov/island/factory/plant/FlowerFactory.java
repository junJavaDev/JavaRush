package ru.javarush.ogarkov.island.factory.plant;

import ru.javarush.ogarkov.island.factory.AbstractFactory;
import ru.javarush.ogarkov.island.entity.plant.Flower;
import ru.javarush.ogarkov.island.factory.Factory;
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
