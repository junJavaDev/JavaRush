package ru.javarush.ogarkov.island.repository.itemfactory.plant;

import ru.javarush.ogarkov.island.repository.itemfactory.AbstractFactory;
import ru.javarush.ogarkov.island.repository.itemfactory.Factory;
import ru.javarush.ogarkov.island.entity.plant.Dandelion;
import ru.javarush.ogarkov.island.settings.Items;

import java.util.concurrent.atomic.AtomicInteger;

public class DandelionFactory extends AbstractFactory {
    public final AtomicInteger count = new AtomicInteger();
    @Override
    public Dandelion createItem() {
        Factory parentFactory = Items.DANDELION.getParent().getFactory();
        created.incrementAndGet();
        parentFactory.addCreatedItem();
        return new Dandelion();
    }
}
